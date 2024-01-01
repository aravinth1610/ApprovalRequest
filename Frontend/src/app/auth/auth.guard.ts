import { Inject, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthStorageService } from './auth-storage.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authStorageServices = inject(AuthStorageService);
  const authRoute = inject(Router);

  if (authStorageServices.getAuthTokenFromCache() != null) {
    const role = route.data['roles'] as Array<string>;
    let isAuthApprvalToRoles = false;
    let isRoles = role.find((fr) =>
      authStorageServices.getAuthRole().includes(fr)
    );
    if (isRoles) {
      isAuthApprvalToRoles = true;
    } else {
      authRoute.navigate(['/login']);
      isAuthApprvalToRoles = false;
    } 
    return isAuthApprvalToRoles;
  } else {
    authRoute.navigate(['/login']);
    return false;
  }
};
