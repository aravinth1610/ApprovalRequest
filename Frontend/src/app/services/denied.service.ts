import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../responsePayload/auth-response';
import { environment } from 'src/environments/environment';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';

@Injectable({
  providedIn: 'root',
})
export class DeniedService {
  constructor(private httpClient: HttpClient) {}

  DeniedDetails(): Observable<AuthResponse[] | HttpErrorResponse> {
    return this.httpClient.get<AuthResponse[]>(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/denied`,
      {
        withCredentials: true,
        observe: 'body',
      }
    );
  }

  DeniedUserDetails(id: number): Observable<AuthResponse | HttpErrorResponse> {
    return this.httpClient.post<AuthResponse>(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/denied/${id}`,
      { withCredentials: true, observe: 'response' }
    );
  }

  updateDeniedStatusToApproval(
    id: number
  ): Observable<any | HttpErrorResponse> {
    return this.httpClient.put(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/denied/approval/${id}`,
      { withcredentials: true }
    );
  }


}
