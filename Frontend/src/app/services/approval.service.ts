import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../responsePayload/auth-response';
import { environment } from 'src/environments/environment';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';

@Injectable({
  providedIn: 'root',
})
export class ApprovalService {
  constructor(private httpClient: HttpClient) {}

  ApprovalDetails(): Observable<AuthResponse[] | HttpErrorResponse> {
    return this.httpClient.get<AuthResponse[]>(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/approval`,
      {
        withCredentials: true,
        observe: 'body',
      }
    );
  }

  ApprovalUserDetails(
    id: number
  ): Observable<AuthResponse | HttpErrorResponse> {
    return this.httpClient.post<AuthResponse>(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/approval/${id}`,
      { withCredentials: true, observe: 'response' }
    );
  }

  updateApprovalStatusToDenied(
    id: number,
    statusMessage: string
  ): Observable<any | HttpErrorResponse> {
    const requestParam = new HttpParams().set('statusMessage', statusMessage);
    return this.httpClient.put(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/approval/denied/${id}`,
      null,
      { params: requestParam, withCredentials: true, observe: 'body' }
    );
  }
}
