import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
  ) {}

  authenticate(emailaddress:string, passWord:string){
    if (emailaddress === "w4tao@uwaterloo.ca" && passWord === "123123") {
      sessionStorage.setItem('authenticatedUser', emailaddress)
      return  true;
    }
    return false;
  }

  isUserLoggedIn(){
   let user= sessionStorage.getItem('authenticatedUser');

   return !(user===null);
  }

  logOut(){
    sessionStorage.removeItem('authenticatedUser');
  }
}
