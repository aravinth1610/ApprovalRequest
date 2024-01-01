import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './compontents/login/login.component';
import { OtherAppLoginComponent } from './compontents/other-app-login/other-app-login.component';
import { OtherAppsignupComponent } from './compontents/other-appsignup/other-appsignup.component';
import { SigninComponent } from './compontents/signin/signin.component';
import { DashboardComponent } from './compontents/dashboard/dashboard.component';
import { PendingComponent } from './compontents/pending/pending.component';
import { ApprovalComponent } from './compontents/approval/approval.component';
import { DeniedComponent } from './compontents/denied/denied.component';
import { ForbiddenComponent } from './compontents/shared/forbidden/forbidden.component';
import { authGuard } from './auth/auth.guard';
import { ViewUserDetailsComponent } from './compontents/view-user-details/view-user-details.component';
import { SignupDialogComponent } from './compontents/dialogBox/signup-dialog/signup-dialog.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'signup',
    component: SigninComponent,
    canDeactivate: [
      (comp: SigninComponent) => {
        return comp.canExists();
      },
    ],
  },
  { path: 'other/app/login', component: OtherAppLoginComponent },
  {
    path: 'other/app/signup',
    component: OtherAppsignupComponent,
    canDeactivate: [
      (comp: SigninComponent) => {
        return comp.canExists();
      },
    ],
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
    data: { roles: ['USER', 'ADMIN'] },
  },
  {
    path: 'pending',
    component: PendingComponent,
    //canActivate: [authGuard],
    //data: { roles: ['ADMIN'] },
  },
  {
    path: 'details',
    children: [
      { path: 'pending/:id', component: ViewUserDetailsComponent },
      { path: 'approval/:id', component: ViewUserDetailsComponent },
      { path: 'denied/:id', component: ViewUserDetailsComponent },
    ],
    //canActivate: [authGuard],
    //data: { roles: ['ADMIN'] },
  },
  {
    path: 'approval',
    component: ApprovalComponent,
    canActivate: [authGuard],
    data: { roles: ['ADMIN'] },
  },
  {
    path: 'denied',
    component: DeniedComponent,
    canActivate: [authGuard],
    data: { roles: ['ADMIN'] },
  },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: '**', component: ForbiddenComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
