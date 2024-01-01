import { Injectable } from '@angular/core';
import { DashboardResponsePayload } from '../responsePayload/dashboard-response-payload';
import { Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor(private httpClient : HttpClient) {}

  data: DashboardResponsePayload[] = [
    {
      name: 'ram',
      gmail: 'a@gmail.com',
      phoneNo: '1234567655',
      companyName: 'r',
      createdOn: '16/10/1888',
      updateOn: '16/06/1222',
      status: 'pending',
    },
    {
      name: 'rav',
      gmail: 'rav@gmail.com',
      phoneNo: '1234',
      companyName: 'r',
      createdOn: '10/10/1888',
      updateOn: '10/06/1222',
      status: 'Approval',
    },
    {
      name: 'rap',
      gmail: 'rep@gmail.com',
      phoneNo: '1234',
      companyName: 'r',
      createdOn: '10/10/1888',
      updateOn: '10/06/1222',
      status: 'Denied',
    },
  ];

  get dummyData() {
    return this.data;
  }

  dashboardDetails(): Observable<DashboardResponsePayload[] | HttpErrorResponse> {
    return this.httpClient
      .get<DashboardResponsePayload[]>(
        `${environment.apiUrl}${PrefixURLConstant.AUTH_URL_API}/dashboard`,
        {
          withCredentials: true,
          observe: 'body',
        }
      )
  }



}
