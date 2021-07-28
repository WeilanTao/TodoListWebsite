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
      localStorage.setItem('authenticatedUser', emailaddress)
      return  true;
    }
    return false;
  }

  isUserLoggedIn(){
   let user= localStorage.getItem('authenticatedUser');

   return !(user===null);
  }

  logOut(){
    localStorage.removeItem('authenticatedUser');
  }
}
