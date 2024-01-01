import { OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Component } from '@angular/core';
import {
  AbstractControl,
  NgModel,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, map, pipe } from 'rxjs';
import { Subscription } from 'rxjs';
import { AppConstant } from 'src/app/constants/app-constant';
import { SignUpRequest } from 'src/app/requestPayload/sign-up-request';
import { UserService } from 'src/app/services/user.service';
import { ToasterService } from 'src/app/toasters/toaster.service';
import { AlreadyGmailIsExistsValidator } from 'src/app/validator/already-gmail-is-exists-validator';
import { GmailMatchValidator } from 'src/app/validator/gmail-match-validator';
import { passwordMatchValidators } from 'src/app/validator/password-match-validator';
import { SignupDialogComponent } from '../dialogBox/signup-dialog/signup-dialog.component';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit, OnDestroy {
  signupFormGroup: FormGroup;
  subscriptions: Subscription[] = [];
  @ViewChild(SignupDialogComponent) signUpDialog: SignupDialogComponent;
  signUpDialogMessage: string = "Do you want to Exist this Page..." ;//AppConstant.signUpDialogMessage;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly alreadyGmailIsExistService: AlreadyGmailIsExistsValidator,
    private readonly userServices: UserService,
    private readonly toasterService: ToasterService,
    private readonly route: Router
  ) {}

  ngOnInit(): void {
    this.signupFormGroup = this.formBuilder.group({
      email: [
        '',
        {
          validators: [
            Validators.required,
            Validators.email,
            GmailMatchValidator,
          ],
          asyncValidators: [
            this.alreadyGmailIsExistService.validate.bind(
              this.alreadyGmailIsExistService
            ),
            //bind() --> creates a live connection,
          ],
          updateOn: 'blur',
        },
      ],
      role: ['', { validators: [Validators.required] }],

      passwordValidator: this.formBuilder.group(
        {
          password: [
            '',
            {
              validators: [
                Validators.required,
                Validators.minLength(6),
                Validators.maxLength(32),
              ],
            },
          ],
          confirmPassword: ['', { validators: [Validators.required] }],
        },
        { validators: [passwordMatchValidators], updateOn: 'blur' }
      ),
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((unsubscription) =>
      unsubscription.unsubscribe()
    );
  }

  canExists(): Promise<boolean> {
    return new Promise((resolve, reject) =>
      this.signUpDialog.showModal(resolve, reject)
    );
  }

  get email() {
    return this.signupFormGroup.get('email');
  }
  get passwordFormGroup() {
    return this.signupFormGroup.get('passwordValidator');
  }
  get password() {
    return this.signupFormGroup.get('passwordValidator.password');
  }
  get confirmPassword() {
    return this.signupFormGroup.get('passwordValidator.confirmPassword');
  }
  get role() {
    return this.signupFormGroup.get('role');
  }

  signUpHandler(): void {
    const signupRequest = new SignUpRequest();
    signupRequest.gmail = this.email.value;
    signupRequest.password = this.password.value;
    signupRequest.confirmPassword = this.confirmPassword.value;
    signupRequest.role = this.role.value;
    this.subscriptions.push(
      this.userServices.createNewUser(signupRequest).subscribe({
        next: (response: any) => {
          console.log(response.status);
          if (response.status === 201) {
            this.toasterService.showSuccess(AppConstant.signupSuccessHeader);
          }
        },
        error: (errorResponse) => {
          console.log(errorResponse);

          const validationErrors = errorResponse.error.validationErrors;
          console.log(validationErrors);
          if (validationErrors != null) {
            Object.keys(validationErrors).forEach((key) => {
              const formControl = this.signupFormGroup.get(key);
              if (formControl) {
                formControl.setErrors({
                  serverError: validationErrors[key],
                });
              }
            });
          } else {
            this.toasterService.showError(AppConstant.signupErrorHeader);
          }
        },
      })
    );
  }
}
