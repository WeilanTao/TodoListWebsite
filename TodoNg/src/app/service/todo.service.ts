import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  // constructor(http: HttpClient) {
  //   super("http://localhost:8080/users/1/todos", http);
  // }

  constructor(
    private http:HttpClient){

  }

  getAllTodoForUser(){
    return this.http.get("http://localhost:8080/todos");

  }
}
