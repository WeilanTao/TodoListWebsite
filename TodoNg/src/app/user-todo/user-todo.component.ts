import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';


export class Todo{
  constructor(
    public todoId:number,
    public todoName:string,
    public description:string, 
    public done:boolean,
    public targetDate:Date,

  ){

  }

}



@Component({
  selector: 'app-user-todo',
  templateUrl: './user-todo.component.html',
  styleUrls: ['./user-todo.component.css']
})
export class UserTodoComponent implements OnInit {

  todos=[
    new Todo(1,'todo1','learn to dance',false,new Date()),
    new Todo(2,'todo2','become an export',false,new Date()),
    new Todo(3,'todo3','visit India',false,new Date),
    new Todo(1,'todo1','learn to dance',false,new Date()),
    new Todo(2,'todo2','become an export',false,new Date()),
    new Todo(3,'todo3','visit India',false,new Date),
    new Todo(1,'todo1','learn to dance',false,new Date()),
    new Todo(2,'todo2','become an export',false,new Date()),
    new Todo(3,'todo3','visit India',false,new Date),
    new Todo(1,'todo1','learn to dance',false,new Date()),
    new Todo(2,'todo2','become an export',false,new Date()),
    new Todo(3,'todo3','visit India',false,new Date),
    new Todo(1,'todo1','learn to dance',false,new Date()),
    new Todo(2,'todo2','become an export',false,new Date()),
    new Todo(3,'todo3','visit India',false,new Date),
    new Todo(1,'todo1','learn to dance',false,new Date()),
    new Todo(2,'todo2','become an export',false,new Date()),
    new Todo(3,'todo3','visit India',false,new Date)

  ]
  

  userid:number=-1;
  constructor(
    private route:Router,
    private activatedRoute:ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.userid=this.activatedRoute.snapshot.params['userid']
  }

}
