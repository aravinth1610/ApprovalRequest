import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';
import { AuthResponse } from '../responsePayload/auth-response';

@Injectable({
  providedIn: 'root',
})
export class PendingService {
  pendingResponseBehvSubject = new BehaviorSubject<AuthResponse[] | null>(null);

  constructor(private httpClient: HttpClient) {}

  PendingDetails(): Observable<AuthResponse[] | HttpErrorResponse> {
    return this.httpClient
      .get<AuthResponse[]>(
        `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/pending`,
        {
          withCredentials: true,
          observe: 'body',
        }
      )
      .pipe(
        tap((pendingRespones: AuthResponse[]) => {
          console.log(pendingRespones);

          this.pendingResponseBehvSubject.next(pendingRespones);
        })
      );
  }

  PendingUserDetails(id: number): Observable<AuthResponse | HttpErrorResponse> {
    return this.httpClient.post<AuthResponse>(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/pending/${id}`,
      { withCredentials: true, observe: 'response' }
    );
  }

  updatePendingStatusToApproval(
    id: number
  ): Observable<any | HttpErrorResponse> {
    return this.httpClient.put(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/pending/approval/${id}`,
      { withcredentials: true }
    );
  }

  updatePendingStatusToDenied(
    id: number,
    statusMessage: string
  ): Observable<any | HttpErrorResponse> {
    console.log(statusMessage, '--');

    const requestParam = new HttpParams().set('statusMessage', statusMessage);
    return this.httpClient.put(
      `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/pending/denied/${id}`,
      null,
      { params: requestParam, withCredentials: true, observe: 'body' }
    );
  }
}
