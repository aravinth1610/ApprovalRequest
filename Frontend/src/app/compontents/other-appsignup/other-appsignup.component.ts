import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AppConstant } from 'src/app/constants/app-constant';
import { OtherSignUpRequest } from 'src/app/requestPayload/other-sign-up-request';
import { OtherStatusRequest } from 'src/app/requestPayload/other-status-request';
import { OtherAppUserService } from 'src/app/services/other-app-user.service';
import { ToasterService } from 'src/app/toasters/toaster.service';
import { GmailMatchValidator } from 'src/app/validator/gmail-match-validator';
import { passwordMatchValidators } from 'src/app/validator/password-match-validator';
import { SignupDialogComponent } from '../dialogBox/signup-dialog/signup-dialog.component';

@Component({
  selector: 'app-other-appsignup',
  templateUrl: './other-appsignup.component.html',
  styleUrls: ['./other-appsignup.component.css'],
})
export class OtherAppsignupComponent implements OnInit, OnDestroy {
  @ViewChild(SignupDialogComponent) signUpDialog: SignupDialogComponent;
  signUpDialogMessage: string = 'Do you want to Exist this Other Page...'; //AppConstant.signUpDialogMessage;
  otherSignupFormGroup: FormGroup;
  subscriptions: Subscription[] = [];
  status: string = 'Need to be Approval';

  constructor(
    private formBuilder: FormBuilder,
    private otherAppUserServices: OtherAppUserService,
    private toasterServices: ToasterService
  ) {}

  ngOnInit(): void {
    this.otherSignupFormGroup = this.formBuilder.group({
      name: [
        '',
        {
          validators: [Validators.required],
          updateOn: 'blur',
        },
      ],
      email: [
        '',
        {
          validators: [
            Validators.required,
            Validators.email,
            GmailMatchValidator,
          ],
          updateOn: 'blur',
        },
      ],
      phoneNo: [
        '',
        {
          validators: [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(16),
          ],
        },
      ],
      companyName: [
        '',
        {
          validators: [
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(54),
          ],
        },
      ],
      status: [this.status, { disabled: false }],
      passwordValidator: this.formBuilder.group({
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
      }),
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

  get name() {
    return this.otherSignupFormGroup.get('name');
  }
  get email() {
    return this.otherSignupFormGroup.get('email');
  }
  get passwordFormGroup() {
    return this.otherSignupFormGroup.get('passwordValidator');
  }
  get password() {
    return this.otherSignupFormGroup.get('passwordValidator.password');
  }
  get confirmPassword() {
    return this.otherSignupFormGroup.get('passwordValidator.confirmPassword');
  }
  get phoneNo() {
    return this.otherSignupFormGroup.get('phoneNo');
  }
  get companyName() {
    return this.otherSignupFormGroup.get('companyName');
  }

  otherSignUpHandler(): void {
    // const otherStatusRequest = new OtherStatusRequest();
    // otherStatusRequest.statusMessage = this.status;

    const otherSignupRequest = new OtherSignUpRequest();
    otherSignupRequest.name = this.name.value;
    otherSignupRequest.gmail = this.email.value;
    otherSignupRequest.password = this.password.value;
    otherSignupRequest.phoneNo = this.phoneNo.value;
    otherSignupRequest.companyName = this.companyName.value;
    //otherSignupRequest.status = otherStatusRequest;

    console.log(otherSignupRequest);

    this.subscriptions.push(
      this.otherAppUserServices.signUp(otherSignupRequest).subscribe({
        next: (response: any) => {
          console.log(response.status);
          if (response.status === 201) {
            this.toasterServices.showSuccess(AppConstant.signupSuccessHeader);
          }
        },
        error: (errorResponse) => {
          console.log(errorResponse);

          const validationErrors = errorResponse.error.validationErrors;
          console.log(validationErrors);
          if (validationErrors != null) {
            Object.keys(validationErrors).forEach((key) => {
              const formControl = this.otherSignupFormGroup.get(key);
              if (formControl) {
                formControl.setErrors({
                  serverError: validationErrors[key],
                });
              }
            });
          } else {
            this.toasterServices.showError(AppConstant.signupErrorHeader);
          }
        },
      })
    );
  }
}
