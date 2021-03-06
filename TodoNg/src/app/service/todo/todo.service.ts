import { CreateTodoPayload } from '../../databoject/createTodo.payload';
import { UpdateTodoPayload } from '../../databoject/update-todo.payload';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppError } from '../../error/app-error';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient) {

  }

  getAllTodoForUser() {
    return this.http.get("http://ec2-35-183-114-6.ca-central-1.compute.amazonaws.com:8080/todos");

  }


  deleteById(todoid: number) {
    let param = new HttpParams();
    param = param.append("todoId", todoid);

    const options: Object = {
      params: param,
      responseType: 'text' as 'tex',
    };

    return this.http.request('delete', "http://ec2-35-183-114-6.ca-central-1.compute.amazonaws.com:8080/deletetodos", options);

  }


  doneById(todoid: number) {
    let param = new HttpParams();
    param = param.append("todoId", todoid);

    const options: Object = {
      params: param,
      responseType: 'text' as 'tex',
    };

    return this.http.request('put', "http://ec2-35-183-114-6.ca-central-1.compute.amazonaws.com:8080/donetodo", options);

  }

  updateTodo(todo: UpdateTodoPayload) {

    return this.http.put("http://ec2-35-183-114-6.ca-central-1.compute.amazonaws.com:8080/updatetodo", todo, { responseType: 'text' });
  }

  createTodo(todo: CreateTodoPayload) {
    return this.http.post("http://ec2-35-183-114-6.ca-central-1.compute.amazonaws.com:8080/createtodos", todo, { responseType: 'text' })
      .pipe(catchError((error: Response) => {
        return throwError(new AppError(error.json));
      }));

  }

}
