import { ConflictError } from './../../common/409error';
import { NotFoundError } from './../../common/404error';
import { AppError } from './../../common/app-error';
import { Observable } from 'rxjs';
import { SignupRequestPayload } from './../../signup/signup-request.payload';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from '@angular/compiler/src/util';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient:HttpClient
  ) { }

  signup(SignupRequestPayload:SignupRequestPayload): Observable<any>{

    return this.httpClient.post('http://localhost:8080/auth/signup',SignupRequestPayload, {responseType:'text'})
    .pipe(catchError((error:Response)=>{
      if(error.status===409){
        return throwError(new ConflictError());
      }
      return throwError(new AppError(error.json));
    }));
  
  }

}
