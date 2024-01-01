import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AppConstant } from '../constants/app-constant';
import { JwtUserModal } from '../modal/jwt-user-modal';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class AuthStorageService {
  private jwtServices = new JwtHelperService();

  constructor(private cookiesServices: CookieService) {}

  // getAuthTokenFromCookies() {
  //   console.log(this.cookiesServices.get("LOGIN_INFO"));
    
  //   return this.cookiesServices.get("LOGIN_INFO");
  // }

  getAuthTokenFromCache(): string {
    return localStorage.getItem(AppConstant.JWTKeyToken);
  }

  storeAuthTokenInCache(authToken: string): void {
    window.localStorage.setItem(AppConstant.JWTKeyToken, authToken);
  }

  getAuthUser(): JwtUserModal {
    const authToken = this.getAuthTokenFromCache();
    if (authToken != null && authToken != '') {
      const authUser = this.jwtServices.decodeToken(authToken);
      if (this.isJWTExpiredToken(authToken)) {
        const jwtTokenModal = new JwtUserModal();
        jwtTokenModal.id = authUser.sub;
        jwtTokenModal.role = authUser.authorities;
        return jwtTokenModal;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  getAuthRole(): string[] {
    const authToken = this.getAuthTokenFromCache();
    if (authToken != null && authToken != '') {
      const authUser = this.jwtServices.decodeToken(authToken);
      if (this.isJWTExpiredToken(authToken)) {
        return authUser.authorities;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  getAuthGmail(): any {
    const authToken = this.getAuthTokenFromCache();
    if (authToken != null && authToken != '') {
      const authGmail = this.jwtServices.decodeToken(authToken);
      if (this.isJWTExpiredToken(authToken)) {
        return authGmail.sub;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  isUserLoggedIn(): boolean {
    const authToken = this.getAuthTokenFromCache();
    if (authToken != null && authToken != '') {
      if (this.getAuthGmail() != null) {
        return true;
      }
    }
    return false;
  }

  isJWTExpiredToken(token: string) {
    return !this.jwtServices.isTokenExpired(token);
  }

  clean(): void {
    window.localStorage.clear();
  }
}
