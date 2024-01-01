import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import {
  EMPTY,
  Observable,
  catchError,
  concatMap,
  switchMap,
  take,
  tap,
  throwError,
} from 'rxjs';
import { AuthStorageService } from '../auth/auth-storage.service';
import { Router } from '@angular/router';
import { AppConstant } from '../constants/app-constant';
import { UserService } from '../services/user.service';
import { PrefixURLConstant } from '../constants/prefix-urlconstant';
import { environment } from 'src/environments/environment';

@Injectable()
export class ClientInterceptor implements HttpInterceptor {
  constructor(
    private readonly authStorageServices: AuthStorageService,
    private readonly route: Router,
    private readonly userServices: UserService
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let httpHeaders = new HttpHeaders();
    let authToken = this.authStorageServices.getAuthTokenFromCache();
    if (authToken != null) {
      httpHeaders = httpHeaders.append(AppConstant.JWTHeader, authToken);
    }
    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');

    const newRequest = request.clone({ headers: httpHeaders });
    return next.handle(newRequest).pipe(
      catchError((err: HttpEvent<any>) => {
        if (err instanceof HttpErrorResponse) {
          switch (err.status) {
            case 403:
              this.route.navigate(['forbidden']);
              break;
            case 401: // handle 401 unauthorized error : try to refresh JWT
              console.log('401---');
              return this.userServices.refreshToken().pipe(
                concatMap(() => next.handle(request)),
                catchError((err) => {
                  console.log(err.status);
                  if (err.status === 403) {
                    this.userServices.logout();
                  }
                  return throwError(() => {
                    this.route.navigate(['/forbidden']);
                  });
                })
              );
          }
        }
        return throwError(() => {
          this.route.navigate(['/forbidden']);
        });
      })
    );
  }
}
