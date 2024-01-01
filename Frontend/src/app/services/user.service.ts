import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppConstant } from '../constants/app-constant';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';
import { SignUpRequest } from '../requestPayload/sign-up-request';
import { SignInRequest } from '../requestPayload/sign-in-request';
import { AuthStorageService } from '../auth/auth-storage.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private http: HttpClient,
    private authStorageServices: AuthStorageService,
    private route: Router
  ) {}

  isGmailAlreadyExists(gmail: string): Observable<Boolean | HttpErrorResponse> {
    const requestParam = new HttpParams().set('gmail', gmail);
    return this.http
      .get(`${environment.apiUrl}${PrefixURLConstant.ALREADY_GMAIL_EXISTS}`, {
        observe: 'response',
        params: requestParam,
      })
      .pipe(
        map((data: any) => {
          return data.body;
        })
        // catchError((error: HttpErrorResponse): Observable<never> =>  {
        //   return throwError(() => error);
        // })
      );
  }

  createNewUser(
    signUpRequestPayload: SignUpRequest
  ): Observable<any | HttpErrorResponse> {
    return this.http.post(
      `${environment.apiUrl}${PrefixURLConstant.SIGNUP_URL_API}`,
      signUpRequestPayload,
      { observe: 'response' }
    );
  }

  loginUser(
    signInRequestPayload: SignInRequest
  ): Observable<void | HttpErrorResponse> {
    console.log(signInRequestPayload);

    return this.http
      .post(
        `${environment.apiUrl}${PrefixURLConstant.LOGIN_URL_API}`,
        signInRequestPayload,
        { observe: 'response', withCredentials: true }
      )
      .pipe(
        map((loginResponse: any) => {
          console.log(loginResponse.headers.get(AppConstant.JWTHeader));

          this.authStorageServices.storeAuthTokenInCache(
            loginResponse.headers.get(AppConstant.JWTHeader)
          );
        })
      );
  }

  refreshToken() {
    console.log('ref');

    return this.http
      .request(
        'POST',
        `${environment.apiUrl}${PrefixURLConstant.REFRESH_TOKEN_URL_API}`,
        {
          observe: 'response',
          withCredentials: true,
        }
      )
      .pipe(
        map((refreshTokenResponse: any) => {
          console.log(refreshTokenResponse.headers.get(AppConstant.JWTHeader));

          this.authStorageServices.storeAuthTokenInCache(
            refreshTokenResponse.headers.get(AppConstant.JWTHeader)
          );
        })
      );
  }

  // refreshTokens() {
  //   return this.http.request(
  //     'post',
  //     'http://localhost:8080/signinrequest/authentication/auth/refress-token',
  //     {
  //       withCredentials: true,
  //       observe: 'response',
  //     }
  //   );
  // }

  logout() {
    return this.http
      .post(
        `${environment.apiUrl}${PrefixURLConstant.LOGOUT_TOKEN_URL_API}`,
        null,
        { withCredentials: true }
      )
      .subscribe({
        next: () => {
          this.authStorageServices.clean();
          window.location.replace('/login');
        },
      });
  }
}
