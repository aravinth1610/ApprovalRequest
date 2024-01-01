import { Injectable } from '@angular/core';
import { SignUpRequest } from '../requestPayload/sign-up-request';
import {
  HttpClient,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';
import { environment } from 'src/environments/environment';
import { Observable, map, tap } from 'rxjs';
import { OtherSignInRequest } from '../requestPayload/other-sign-in-request';
import { OtherSignUpRequest } from '../requestPayload/other-sign-up-request';

@Injectable({
  providedIn: 'root',
})
export class OtherAppUserService {
  constructor(private http: HttpClient) {}

  signUp(
    signUp: OtherSignUpRequest
  ): Observable<any | HttpErrorResponse> {
    return this.http.post<OtherSignUpRequest>(
      `${environment.apiUrl}${PrefixURLConstant.OTHER_SIGNUP_URL_API}`,
      signUp,
      {observe: 'response'},
    );
  }

  login(
    singIn: OtherSignInRequest
  ): Observable<any | HttpErrorResponse> {
    return this.http.post<OtherSignUpRequest | HttpErrorResponse>(
      `${environment.apiUrl}${PrefixURLConstant.OTHER_LOGIN_URL_API}`,
      singIn,
      {observe: 'response'}
    );
  }
}
