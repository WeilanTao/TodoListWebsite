import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup , Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // email:string="";
  // password:string="";
  invalidLogin:boolean=false;
  isShow:boolean=false;
  isSubmitted=false;

  loginform= new FormGroup({
    'account': new FormGroup({
      'email':new FormControl('',[
        Validators.required,
        Validators.email
      ]),
      'password': new FormControl('',[
        Validators.required,
        
      ])
    })
  })
  
  get email(){
    return this.loginform.get('account.email');
  }
  get password(){
    return this.loginform.get('account.password');
  }

  changeShow(){
    this.isShow=!this.isShow;
  }
  
  constructor(
    private router: Router, //maps a URL path to a component
    private routr: ActivatedRoute  ) {
      
   }

  ngOnInit(): void {
    

  }

  inputEmail(userEmail:HTMLInputElement){
    this.loginform.get('account.email')?.setValue(userEmail.value);
    // console.log(this.loginform.get('account.email'));
  }

  inputPassword(userPassword:HTMLInputElement){
    this.loginform.get('account.password')?.setValue(userPassword.value);
  }



  login(){
    // this.loginform.setErrors({
    //   invalidLogin:true
    // });
    let emailaddress= this.loginform.get('account.email')?.value as string;
    let passWord= this.loginform.get('account.password')?.value as string;
    if(emailaddress==="w4tao@uwaterloo.ca" && passWord==="123123"){
      this.router.navigate(['todo',emailaddress]); 
      this.invalidLogin=false;
    }else{
      console.log(111111);
      
      this.invalidLogin=true;
      // this.loginform.get('account.email')?.setValue('');
      // this.loginform.get('account.password')?.setValue('');
      this.loginform.reset();
      console.log("invalidLogin", this.invalidLogin, "email", this.loginform.get('account.email')?.touched);
    }
    
  }


}
