import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './compontents/login/login.component';
import { OtherAppLoginComponent } from './compontents/other-app-login/other-app-login.component';
import { OtherAppsignupComponent } from './compontents/other-appsignup/other-appsignup.component';
import { SigninComponent } from './compontents/signin/signin.component';
import { DashboardComponent } from './compontents/dashboard/dashboard.component';
import { PendingComponent } from './compontents/pending/pending.component';
import { ApprovalComponent } from './compontents/approval/approval.component';
import { DeniedComponent } from './compontents/denied/denied.component';
import { HeaderComponent } from './compontents/shared/header/header.component';
import { ForbiddenComponent } from './compontents/shared/forbidden/forbidden.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModel, ReactiveFormsModule } from '@angular/forms';
import { ClientInterceptor } from './interceptor/client.interceptor';
import { NgToastModule } from 'ng-angular-popup';
import { ToasterService } from './toasters/toaster.service';
import { ViewUserDetailsComponent } from './compontents/view-user-details/view-user-details.component';
import { PendingService } from './services/pending.service';
import { authGuard } from './auth/auth.guard';
import { AuthStorageService } from './auth/auth-storage.service';
import { DashboardService } from './services/dashboard.service';
import { SignupDialogComponent } from './compontents/dialogBox/signup-dialog/signup-dialog.component';
import { StatusDialogBoxComponent } from './compontents/dialogBox/status-dialog-box/status-dialog-box.component';
//npm i ng-angular-popup
//npm install bootstrap@5
//npm install jquery
//npm install @angular/animations --save
//npm install @fortawesome/fontawesome-free
//npm i ng-angular-popup(https://www.npmjs.com/package/ng-angular-popup)
//npm install @auth0/angular-jwt
//npm install ngx-cookie-service@16.1.0 --save --It ill not work

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OtherAppLoginComponent,
    OtherAppsignupComponent,
    SigninComponent,
    DashboardComponent,
    PendingComponent,
    ApprovalComponent,
    DeniedComponent,
    HeaderComponent,
    ForbiddenComponent,
    ViewUserDetailsComponent,
    SignupDialogComponent,
    StatusDialogBoxComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgToastModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ClientInterceptor, multi: true },
    ToasterService,
    PendingService,
    AuthStorageService,
    DashboardService,
    NgModel
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
