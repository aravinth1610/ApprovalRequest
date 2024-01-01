import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AppConstant } from 'src/app/constants/app-constant';
import { OtherSignInRequest } from 'src/app/requestPayload/other-sign-in-request';
import { OtherAppUserService } from 'src/app/services/other-app-user.service';
import { ToasterService } from 'src/app/toasters/toaster.service';

@Component({
  selector: 'app-other-app-login',
  templateUrl: './other-app-login.component.html',
  styleUrls: ['./other-app-login.component.css'],
})
export class OtherAppLoginComponent implements OnInit, OnDestroy {
  loginFormGroup: FormGroup;
  subscription: Subscription[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private otherAppUserServices: OtherAppUserService,
    private toasterServices: ToasterService
  ) {}

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      gmail: ['', { validators: [Validators.required, Validators.email] }],
      password: ['', { validators: [Validators.required] }],
    });
  }

  ngOnDestroy(): void {
    this.subscription.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

  get gmail() {
    return this.loginFormGroup.get('gmail');
  }

  get password() {
    return this.loginFormGroup.get('password');
  }

  handleLogin() {
    const signin = new OtherSignInRequest();
    signin.gmail = this.gmail.value;
    signin.password = this.password.value;
    this.otherAppUserServices.login(signin).subscribe({
      next: (signInRequestRespone: any) => {
        this.toasterServices.showSuccess(AppConstant.LoginSuccessHeader);
      },
      error: (errorResponse: HttpErrorResponse) => {
        if (errorResponse.status != 403) {
          const validationErrors = errorResponse.error.validationErrors;
          console.log(validationErrors);

          if (validationErrors != null) {
            Object.keys(validationErrors).forEach((key) => {
              const formControl = this.loginFormGroup.get(key);
              if (formControl) {
                formControl.setErrors({
                  serverError: validationErrors[key],
                });
              }
            });
          } else {
            this.toasterServices.showError(
              AppConstant.InvalidUsernamePasswordErrorHeader
            );
          }
        } else {
          this.toasterServices.showError(
            AppConstant.InvalidPasswordErrorHeader
          );
        }
      },
    });
  }
}
