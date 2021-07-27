import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserTodoComponent } from './user-todo/user-todo.component';

const routes: Routes = [
  { path: 'mytodo', component: HomeComponent },
  { path: 'mytodo/login', component: LoginComponent },
  { path: 'mytodo/:name', component: UserTodoComponent },
  { path: 'mytodo/users/:name/todosById', component: UserTodoComponent },
  // { path: 'MyTodo/:name/:projectname', component: UserTodoComponent },
  { path: '**', component: ErrorpageComponent }//this has to be the last one in the list; otherwise it will filter out anything below it
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
