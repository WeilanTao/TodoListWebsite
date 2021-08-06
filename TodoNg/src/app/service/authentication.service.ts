import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import {map} from  'rxjs/operators';

export class AuthenticatinBean{
  constructor(
    public id:string,
    public userName:string,
    public password:string,
    public email:string,
    public idAdmin:boolean

  ){
  }
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http:HttpClient
  ) {}


  authenticate(email:string, passWord:string){
    let authHeaderString= 'Basic '+window.btoa(email+":"+passWord);
    let headers= new HttpHeaders({
        Authorization:authHeaderString
    });
  
    return this.http.get<AuthenticatinBean>(
      "http://localhost:8080/account", {headers}
    ).pipe(
      map(
        data=>{
          localStorage.setItem('authenticatedUser', email);
          localStorage.setItem('token',email);
          return data;
        }
      )
    );
  }

  getAuthenticatedUser(){
    return sessionStorage.getItem('authenticatedUser');
  }

  getAuthenticatedToken(){
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem('authenticatedToken');
    return null;
  }

  isUserLoggedIn(){
   let user= localStorage.getItem('authenticatedUser');

   return !(user===null);
  }

  logOut(){
    localStorage.removeItem('authenticatedUser');
    localStorage.removeItem('token');
  }
}
