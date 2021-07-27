import { TodoDataService } from './../service/data/todo-data.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-todo',
  templateUrl: './user-todo.component.html',
  styleUrls: ['./user-todo.component.css']
})
export class UserTodoComponent implements OnInit {

  todos: UserTodoComponent[]=[];
  // todos={
  //   id:1,
  //   description:'learn to Dance'
  // }

  name:number=-1;
  constructor(
    private route:Router,
    private activatedRoute:ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.name=this.activatedRoute.snapshot.params['name']
  }

}
