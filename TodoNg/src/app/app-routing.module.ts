import { UsersComponent } from './users/users.component';
import { RouteGuardService } from './service/route-guard.service';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserTodoComponent } from './user-todo/user-todo.component';
import { SignupComponent } from './signup/signup.component';
import { AdminpageComponent } from './adminpage/adminpage.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent},
  { path: 'adminpage/:username/access', component: AdminpageComponent}, //TODO: canActivate
  { path: 'admin/:username/allusers' , component: UsersComponent},
  { path: 'user/:username/todos', component: UserTodoComponent },//, canActivate:[RouteGuardService]
  { path: '**', component: ErrorpageComponent }//this has to be the last one in the list; otherwise it will filter out anything below it
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
