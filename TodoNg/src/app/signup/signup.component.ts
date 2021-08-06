import { ConflictError } from './../common/409error';
import { NotFoundError } from './../common/404error';
import { AppError } from './../common/app-error';
import { AuthService } from './../service/auth/auth.service';
import { SignupRequestPayload } from './signup-request.payload';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload:SignupRequestPayload;

  signupform = new FormGroup({
    'account': new FormGroup({
      'username': new FormControl('', [
        Validators.required,
        Validators.maxLength(20)
      ]),
      'email': new FormControl('', [
        Validators.email,
        Validators.required
      ]),
      'password': new FormControl('', [
        Validators.required
      ])
    })
  })

  isShow: boolean = false;

  get username() {
    return this.signupform.get('account.username');
  }

  get email() {
    return this.signupform.get('account.email');
  }

  inputEmail(userEmail: HTMLInputElement) {
    this.signupform.get('account.email')?.setValue(userEmail.value);
  }

  inputPassword(userPassword: HTMLInputElement) {
    this.signupform.get('account.password')?.setValue(userPassword.value);
  }

  inputUsername(userName: HTMLInputElement){
    this.signupform.get('account.username')?.setValue(userName.value);
  }


  get password() {
    return this.signupform.get('account.password');
  }
  userExists: boolean = false;

  constructor(private authService:AuthService) {
    this.signupRequestPayload={
      username:'',
      useremail:'',
      password: '',
      isAdmin: false
    }
   }

  ngOnInit(): void {
  }

  changeShow() {
    this.isShow = !this.isShow;
  }

  signup() {
    this.signupRequestPayload.useremail = this.signupform.get('account.email')?.value as string;
    this.signupRequestPayload.username=this.signupform.get('account.username')?.value as string;
    this.signupRequestPayload.password=this.signupform.get('account.password')?.value as string;
    this.signupRequestPayload.isAdmin= false;

    this.authService.signup(this.signupRequestPayload)
      .subscribe(data=>{
        console.log(data);
      },(error: AppError)=>{
        this.signupform.reset();

        if(error instanceof ConflictError){
          this.userExists = true;
        }else{
          this.signupform.setErrors(error.originalError);
          throw error;
        }
      })
  }

}
