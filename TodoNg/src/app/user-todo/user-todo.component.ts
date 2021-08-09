import { TodoService } from './../service/todo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';






@Component({
  selector: 'app-user-todo',
  templateUrl: './user-todo.component.html',
  styleUrls: ['./user-todo.component.css']
})
export class UserTodoComponent implements OnInit {

  notEmpty: any;
  todos: any;
  isDeleted = false;

  displayedColumns = ['name', 'description', 'date', 'isDone', 'option'];

  username: string = '';
  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private todoService: TodoService,
  ) { }

  ngOnInit(): void {

    this.username = this.activatedRoute.snapshot.params['username'];

    this.loadTodoList();

  }

  loadTodoList() {
    this.todoService.getAllTodoForUser()
      .subscribe(response => {

        this.notEmpty = response.toString() === "" ? true : false;

        this.todos = response;

      })

  }


  //TODO: Autohide alert box~animation
  deleteTodo(id: number) {
    this.todoService.deleteById(id)
      .subscribe(
        response => {
          this.isDeleted = true;
          this.loadTodoList();
        }
      );
  }

  doneTodo(id:number){
    this.todoService.doneById(id)
    .subscribe(
      response => {
        this.isDeleted = true;
        this.loadTodoList();
      }
    );
  }

}
