import { TodoService } from './../service/todo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';


export class Todo{
  constructor(
    public todo_id:number,
    public todoName:string,
    public description:string, 
    public isDone:boolean,
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
  

  username:string='';
  constructor(
    private route:Router,
    private activatedRoute:ActivatedRoute,
    private todoService:TodoService,
  ) { }

  ngOnInit(): void {

    this.username=this.activatedRoute.snapshot.params['username'];

    this.loadTodoList();
    
  }

  loadTodoList(){
    this.todoService.getAllTodoForUser()
      .subscribe(response=>{
        console.log(response);
        this.todos=response;
      })
    
  }


  //TODO: Autohide alert box~animation
  deleteTodo(id:number){
    // this.todoService.deleteById(id)
    //   .subscribe(
    //     response=>{
    //       this.isDeleted=true;
    //       this.loadTodoList();
    //     }
    //   );
  }


}
