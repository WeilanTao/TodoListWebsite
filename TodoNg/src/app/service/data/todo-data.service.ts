import { Injectable } from '@angular/core';
import {HttpClient} from  '@angular/common/http';
import { UserTodoComponent } from 'src/app/user-todo/user-todo.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(
    private http:HttpClient
  ) { }

  // retriveAllTodos(userid: number){
  //   return this.http.get<Todo[]>(`http://localhost:8080/mytodo/users/${userid}/todosById`)
  // }
}
