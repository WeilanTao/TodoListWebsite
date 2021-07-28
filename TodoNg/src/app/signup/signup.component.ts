import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupform= new FormGroup({
    'account': new FormGroup({
      'username': new FormControl('',[
        Validators.required,
        Validators.maxLength(20)
      ]),
      'email': new FormControl('',[
        Validators.email,
        Validators.required
      ]),
      'password': new FormControl('',[
        Validators.required
      ])
    })
  })

  userExists:boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

 

  signup(){
    let email= this.signupform.get('account.email')?.value as string;
    if(email === "w4tao@uwaterloo.ca"){
      this.userExists=true;
      this.signupform.reset();
    }

  }

}
