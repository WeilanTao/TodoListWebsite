import { catchError, filter, finalize, switchMap, take } from 'rxjs/operators';
import { AuthService } from './../auth/auth.service';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { RefreshTokenPayload } from 'src/app/databoject/refreshtoken.payload';
import { LoginResponsePayload } from 'src/app/databoject/login-response.payload';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

//with HttpClient + this interceptor(Http won't work here), the accesstoken will be attached to the header whenever making a HTTP request
export class HttpIntercepterService implements HttpInterceptor {

  private isRefreshing = false;
  private refershTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const accessToken = this.authService.getAuthenticatedToken();
    const refreshToken = this.authService.getRefreshToken();

    if (accessToken && refreshToken) {
      req = this.addToken(req, accessToken);

      //checking token expiry: by receiving response form backend!
      return next.handle(req)       //pass the request to the next interceptor in the chain if there is one
        .pipe(catchError(error => {

          if (error instanceof HttpErrorResponse && error.status === 401) {
            //if the server gives a 401(when the access token expires; we try to call the refeshtoken method to get a new token)
            return this.handle401AuthErrors(req, next);

          } else {
            throw error;

          }
        }))
        ;
    }

    return next.handle(req);

  }

  private handle401AuthErrors(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      //branch: executing token refeshing

      //we enter this branch when the refershing hasn't started yet and we set the isRefreshing to true to indicate the refeshing is in process
      this.isRefreshing = true;
      this.refershTokenSubject.next(null);//block the following request

      let refreshTokenPayload: RefreshTokenPayload = {
        useremail: this.authService.getAuthenticatedUserEmail(),
        refreshtoken: this.authService.getRefreshToken(),
      }
      //calling backend to get a new accesstoken
      return this.authService.refreshtoken(refreshTokenPayload).pipe(
        //switch to a new observable: where you are no longer concerned with the response of the previous request when a new input arrives. 
        switchMap((loginResponsePayload: LoginResponsePayload) => {
          let newaccesstoken = loginResponsePayload.accessToken;
          this.refershTokenSubject.next(newaccesstoken);
          return next.handle(this.addToken(req,
            newaccesstoken));
        }),
        finalize(() => this.isRefreshing = false)
      );

    } else {
      //branch: blocking and releasing all the other queries that started during thre refreshing process 
      //which we want to put on hold untill we have a new access token

      //during the moment when the refeshing is happing, the value in the refreshTokenSubject is null;
      //the filter blockes all the requests untill the value in the refreshTokenSubject is not null(as long as we get the new token);
      // take(1) transform the refershTokenSubject to observable 
      //and then release the query that initialized the query 
      return this.refershTokenSubject.pipe(
        filter(token => token != null),
        //take is the opposite of skip where take will take the first n number of emissions while skip will skip the first n number of emissions.
        take(1),
        switchMap(jwt => {
          return next.handle(this.addToken(req,
            jwt));
        })
      )
    }


  }

  private addToken(req: HttpRequest<any>, accesstoken: string) {

    if (!req.url.match(/localhost:8080\//)) {
      return req;
    }

    let basicAuthHeader = 'Bearer ' + accesstoken;

    //as the original request is immutable, we clone the request to add a header
    //we add an Authorication header with the Bearer JWT token
    req = req.clone({
      setHeaders: {
        Authorization: basicAuthHeader
      }
    })

    return req;

  }

}
