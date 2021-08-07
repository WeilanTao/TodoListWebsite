import { catchError, filter, switchMap, take } from 'rxjs/operators';
import { AuthService } from './../auth/auth.service';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { RefreshTokenPayload } from 'src/app/databoject/refreshtoken.payload';
import { LoginResponsePayload } from 'src/app/databoject/login-response.payload';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterService implements HttpInterceptor {

  private isRefreshing = false;
  private refershTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(
    private authService: AuthService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!(req.url.indexOf('refresh') === -1) || !(req.url.indexOf('login') === -1) || !(req.url.indexOf('signup') === -1) || !(req.url.indexOf('logout') === -1)) {
      return next.handle(req);
    }


    const accessToken = this.authService.getAuthenticatedToken();
    const refreshToken = this.authService.getRefreshToken();

    console.log(accessToken, refreshToken);

    if (accessToken && refreshToken) {
      req = this.addToken(req, accessToken);
      return next.handle(req)
        .pipe(catchError(error => {
          console.log(error instanceof HttpResponse);

          if (error instanceof HttpErrorResponse && error.status === 401) {

            return this.handle401AuthErrors(req, next);
          } else {
            throw error;
          }
        }))
        ;
    }

    //checking token expiry: by receiving response form backend!
    return next.handle(req);

  }

  private handle401AuthErrors(req: HttpRequest<any>, next: HttpHandler)
    : Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refershTokenSubject.next(null);

      let refreshTokenPayload: RefreshTokenPayload = {
        useremail: this.authService.getAuthenticatedUserEmail(),
        refreshtoken: this.authService.getRefreshToken(),
      }

      return this.authService.refreshtoken(refreshTokenPayload).pipe(
        switchMap((loginResponsePayload: LoginResponsePayload) => {
          this.isRefreshing = false;
          let newaccesstoken = loginResponsePayload.accessToken;
          this.refershTokenSubject.next(newaccesstoken);
          return next.handle(this.addToken(req,
            newaccesstoken));
        })
      );

    } else {
      return this.refershTokenSubject.pipe(
        filter(token => token != null),
        take(1),
        switchMap(jwt => {
          return next.handle(this.addToken(req,
            jwt));
        })
      )
    }


  }

  private addToken(req: HttpRequest<any>, accesstoken: string) {
    let basicAuthHeader = 'Bearer ' + accesstoken;
    req = req.clone({
      setHeaders: {
        Authorization: basicAuthHeader
      }
    })

    return req;

  }

}
