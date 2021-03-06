import { AuthService } from './../service/auth/auth.service';
import { UpdateTodoPayload } from './../databoject/update-todo.payload';
import { UpdateTodoComponent } from './../update-todo/update-todo.component';
import { TodoService } from '../service/todo/todo.service';
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

  isAdmin:boolean = this.authservice.getAuthenticatedUserRole()==="ADMIN"?true:false;

  displayedColumns = ['name', 'description', 'date', 'isDone', 'option'];

  // username: string = '';
  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private todoService: TodoService,
    private dialog: MatDialog,
    private authservice:AuthService
  ) { }

  ngOnInit(): void {

    // this.username = this.activatedRoute.snapshot.params['username'];

    this.loadTodoList();

  }

 
  goAdminPage(){
    this.route.navigate(['admin','access'])
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
    const dialogRef = this.dialog.open(
      UpdateTodoComponent,
      {
        disableClose:true,
        autoFocus : false,
        width : "300px",
        height: "200px",
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
      // console.log('The dialog was closed');
      this.loadTodoList();
    });
  }

  createTodo() {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = false;
    dialogConfig.width = "500px";
    dialogConfig.height = "300px";
    const dialogRef = this.dialog.open(CreateTodoComponent, dialogConfig);
    dialogRef.afterClosed()
      .subscribe(result => {
        // console.log('The dialog was ceated');
        this.loadTodoList();
    });

  }

}
