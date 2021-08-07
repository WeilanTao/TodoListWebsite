import { LoginRequestPayload } from './../databoject/login-request.payload';
import { AuthService } from './../service/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  invalidLogin: boolean = false;
  isShow: boolean = false;
  isSubmitted = false;

  loginform = new FormGroup({
    'account': new FormGroup({
      'email': new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      'password': new FormControl('', [
        Validators.required,

      ])
    })
  })

  get email() {
    return this.loginform.get('account.email');
  }
  get password() {
    return this.loginform.get('account.password');
  }

  changeShow() {
    this.isShow = !this.isShow;
  }

  constructor(
    private router: Router, //maps a URL path to a component
    private routr: ActivatedRoute,
    private authService:AuthService
    // private authenticationService:AuthenticationService
    ) {

  }

  ngOnInit(): void {


  }

  inputEmail(userEmail: HTMLInputElement) {
    this.loginform.get('account.email')?.setValue(userEmail.value);
    // console.log(this.loginform.get('account.email'));
  }

  inputPassword(userPassword: HTMLInputElement) {
    this.loginform.get('account.password')?.setValue(userPassword.value);
  }



  // loginHardCoded() {
  //   // this.loginform.setErrors({
  //   //   invalidLogin:true
  //   // });
  //   let emailaddress = this.loginform.get('account.email')?.value as string;
  //   let passWord = this.loginform.get('account.password')?.value as string;
  //   if (this.authenticationService.authenticate(emailaddress, passWord)) {
  //     this.router.navigate(['user',emailaddress, 'todos']);
  //     this.invalidLogin = false;
  //   } else {
  //     this.invalidLogin = true;
  //     this.loginform.reset();
  //     console.log("invalidLogin", this.invalidLogin, "email", this.loginform.get('account.email')?.touched);
  //   }
  // }

  // login() {
  //   let emailaddress = this.loginform.get('account.email')?.value as string;
  //   let passWord = this.loginform.get('account.password')?.value as string;
  //   if (this.authenticationService.authenticate(emailaddress, passWord)) {
  //     this.router.navigate(['user',emailaddress, 'todos']);
  //     this.invalidLogin = false;
  //   } else {
  //     this.invalidLogin = true;
  //     this.loginform.reset();
  //     console.log("invalidLogin", this.invalidLogin, "email", this.loginform.get('account.email')?.touched);
  //   }
  // }

  login() {
    let emailaddress = this.loginform.get('account.email')?.value as string;
    let passWord = this.loginform.get('account.password')?.value as string;
    console.log(emailaddress, passWord);
    let loginRequestPayload: LoginRequestPayload={userEmail:emailaddress, passWord:passWord};
    this.authService.login(loginRequestPayload )
      .subscribe(
        data=>{
          let role = this.authService.getAuthenticatedUserRole();
          let username = this.authService.getAuthenticatedUserName();
          if(role==="ADMIN"){
            this.router.navigate(['adminpage', username,'access'])
          }else if(role==="USER"){
            this.router.navigate(['user',username, 'todos']);
          }
          this.invalidLogin = false;
        },
        error=>{
          console.log(error);
          this.invalidLogin = true;
          this.loginform.reset();
          // console.log("invalidLogin", this.invalidLogin, "email", this.loginform.get('account.email')?.touched);
        
        }
      )

  }

  goSignupUser(){
    this.loginform.reset();
    this.loginform.setErrors(null);    
    this.invalidLogin=false;
    this.router.navigate(['/signup']);
  }


}
