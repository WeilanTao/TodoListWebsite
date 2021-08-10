import { UpdateTodoPayload } from './../databoject/update-todo.payload';
import { UpdateTodoComponent } from './../update-todo/update-todo.component';
import { TodoService } from './../service/todo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { CreateTodoComponent } from '../create-todo/create-todo.component';


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
    private dialog: MatDialog
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

  doneTodo(id: number) {
    this.todoService.doneById(id)
      .subscribe(
        response => {
          this.isDeleted = true;
          this.loadTodoList();
        }
      );
  }


  editTodo(todo: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "600px";
    dialogConfig.height = "300px";
    const dialogRef = this.dialog.open(
      UpdateTodoComponent,
      {
        // dialogConfig

        data: {
          todo_id: todo.todo_id,
          name: todo.name,
          description: todo.description,
          date: todo.date,
          isDone: todo.isDone
        }
      }
    );

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.loadTodoList();
    });
  }

  createTodo() {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = false;
    dialogConfig.width = "600px";
    dialogConfig.height = "300px";
    const dialogRef = this.dialog.open(CreateTodoComponent, dialogConfig);
    dialogRef.afterClosed()
      .subscribe(result => {
        console.log('The dialog was ceated');
        this.loadTodoList();
    });

  }

}
