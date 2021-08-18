import { LoginResponsePayload } from './../../databoject/login-response.payload';
import { LoginRequestPayload } from './../../databoject/login-request.payload';
import { ConflictError } from '../../error/409error';
import { AppError } from '../../error/app-error';
import { Observable } from 'rxjs';
import { SignupRequestPayload } from '../../databoject/signup-request.payload';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { UnauthorizedError } from 'src/app/error/401';
import { LocalStorageService } from 'ngx-webstorage';
import { RefreshTokenPayload } from 'src/app/databoject/refreshtoken.payload';
import { ForbiddenError } from 'src/app/error/403Error';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient: HttpClient,
    private localStorage: LocalStorageService
  ) { }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {

    return this.httpClient.post('http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/auth/signup', signupRequestPayload, { responseType: 'text' })
      .pipe(catchError((error: Response) => {
        if (error.status === 409) {
          return throwError(new ConflictError());
        }
        return throwError(new AppError(error.json));
      }));

  }

  //Tell HttpClient that you want the full response with the observe option of the posy()
  //Now HttpClient.post() returns an Observable of type HttpResponse rather than just the JSON data contained in the body.
  login(loginRequestPayload: LoginRequestPayload): Observable<Boolean> {

    return this.httpClient.post<LoginResponsePayload>('http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/auth/login', loginRequestPayload)
      .pipe(map(data => {
        this.localStorage.store('accesstoken', data.accessToken);
        this.localStorage.store('username', data.username);
        this.localStorage.store('refreshToken', data.refreshToken);
        this.localStorage.store('useremail', data.useremail);
        this.localStorage.store('userrole', data.role);

        return true;
      })
      )
      .pipe(catchError((error: Response) => {
        if (error.status === 401) {
          return throwError(new UnauthorizedError());
        }else if(error.status === 403){
          return throwError(new ForbiddenError());
        }
        return throwError(new AppError(error.json));
      }));
  }

  refreshtoken(refreshTokenPayload: RefreshTokenPayload): Observable<any> {
    return this.httpClient.post<LoginResponsePayload>("http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/auth/refreshtoken", refreshTokenPayload)
      .pipe(tap(data => {
        this.localStorage.clear('accesstoken');
        this.localStorage.store('accesstoken', data.accessToken);
      }));

  }

  logOut() {

    let refreshTokenPayload: RefreshTokenPayload = {
      useremail: this.getAuthenticatedUserEmail(),
      refreshtoken: this.getRefreshToken()
    }

    this.localStorage.clear();

    return this.httpClient.post("http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/auth/logout", refreshTokenPayload, { responseType: 'text' })
      .subscribe(data => { });

  }

  getAuthenticatedUserName() {
    return this.localStorage.retrieve('username');
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUserName())
      return this.localStorage.retrieve('accesstoken');
    return null;
  }

  getRefreshToken() {
    if (this.getAuthenticatedUserName())
      return this.localStorage.retrieve('refreshToken');
    return null;
  }

  getAuthenticatedUserEmail() {
    return this.localStorage.retrieve('useremail');
  }

  getAuthenticatedUserRole() {
    return this.localStorage.retrieve('userrole');
  }

  isUserLoggedIn() {
    let name = this.getAuthenticatedUserName();
    let refreshToken = this.getRefreshToken();
    let accessToken = this.getAuthenticatedToken();

    return !(name === null || refreshToken === null || accessToken === null);
  }


}
