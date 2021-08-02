import { TodoService } from './../service/todo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';


export class Todo{
  constructor(
    public todo_id:number,
    public todoName:string,
    public description:string, 
    public done:boolean,
    public date:Date,

  ){

  }

}



@Component({
  selector: 'app-user-todo',
  templateUrl: './user-todo.component.html',
  styleUrls: ['./user-todo.component.css']
})
export class UserTodoComponent implements OnInit {

  todos:any;
  isDeleted= false;
  

  userid:number=-1;
  constructor(
    private route:Router,
    private activatedRoute:ActivatedRoute,
    private todoService:TodoService,
  ) { }

  ngOnInit(): void {
    this.userid=this.activatedRoute.snapshot.params['userid'];

    this.loadTodoList();
    
  }

  loadTodoList(){
    this.todoService.getAll()
      .subscribe(response=>{
        this.todos=response;
      })
    
  }


  //TODO: Autohide alert box~animation
  deleteTodo(id:number){
    this.todoService.deleteById(id)
      .subscribe(
        response=>{
          this.isDeleted=true;
          this.loadTodoList();
        }
      );
  }


}
