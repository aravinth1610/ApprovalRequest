import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthStorageService } from 'src/app/auth/auth-storage.service';
import { AppConstant } from 'src/app/constants/app-constant';
import { SignInRequest } from 'src/app/requestPayload/sign-in-request';
import { UserService } from 'src/app/services/user.service';
import { ToasterService } from 'src/app/toasters/toaster.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit, OnDestroy {
  loginFormGroup: FormGroup;
  subscriptions: Subscription[] = [];

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly userServices: UserService,
    private readonly toast: ToasterService,
    private readonly route: Router,
    private readonly authStorageService: AuthStorageService
  ) {
    if (this.authStorageService.isUserLoggedIn()) {
      this.route.navigate(['/dashboard']);
    }
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      gmail: ['', { validators: [Validators.required, Validators.email] }],
      password: ['', { validators: [Validators.required] }],
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((unsubscription) => {
      unsubscription.unsubscribe();
    });
  }

  get gmail() {
    return this.loginFormGroup.get('gmail');
  }

  get password() {
    return this.loginFormGroup.get('password');
  }

  handleLogin() {
    const signin = new SignInRequest();
    signin.gmail = this.gmail.value;
    signin.password = this.password.value;
    console.log(signin);

    this.subscriptions.push(
      this.userServices.loginUser(signin).subscribe({
        next: (signInResponse: void) => {
          this.toast.showSuccess('user Successfully Login');
          window.location.replace('/dashboard');
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
              this.toast.showError(
                AppConstant.InvalidUsernamePasswordErrorHeader
              );
            }
          } else {
            this.toast.showError(AppConstant.InvalidPasswordErrorHeader);
          }
        },
      })
    );
  }
}
