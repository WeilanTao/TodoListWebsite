import { catchError } from 'rxjs/operators';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

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
  

  deleteById(todoid:number){
    let param= new HttpParams();
    param= param.append("todoId", todoid);

    const options:Object = {
      params: param,
      responseType:'text' as 'tex',
    };

    return this.http.request('delete', "http://localhost:8080/deletetodos", options);
  
  }


  doneById(todoid:number){
    let param= new HttpParams();
    param= param.append("todoId", todoid);

    const options:Object = {
      params: param,
      responseType:'text' as 'tex',
    };

    return this.http.request('put', "http://localhost:8080/donetodo", options);
  
  }

}
