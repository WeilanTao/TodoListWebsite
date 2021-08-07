import { ErrorHandler } from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserTodoComponent } from './user-todo/user-todo.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpIntercepterService } from './service/http/http-intercepter.service';
import { AppErrorHandler } from './error/app-error-handler';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { NgxWebstorageModule } from 'ngx-webstorage';

import {MatCardModule} from '@angular/material/card';



@NgModule({
  declarations: [//the following components are part of the specific module
    AppComponent,
    LoginComponent,
    HomeComponent,
    UserTodoComponent,
    ErrorpageComponent,
    HeaderComponent,
    FooterComponent,
    SignupComponent,
    AdminpageComponent,
  
  ],
  imports: [//the components in this Module need the following Module dependencies to work
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot(),
    MatCardModule
  ],
  providers: [
    {provide: ErrorHandler, useClass:AppErrorHandler}, //whenever you are using errorhandle in the project, use AppErrorHandler instead
    {provide:HTTP_INTERCEPTORS, useClass:HttpIntercepterService, multi:true}
  ],
  bootstrap: [AppComponent] //when this module loads, theses bootstrap components will be loaded at the same time 
})
export class AppModule { }
