import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-todo',
  templateUrl: './user-todo.component.html',
  styleUrls: ['./user-todo.component.css']
})
export class UserTodoComponent implements OnInit {

  todo={
    id:1,
    description:'learn to Dance'
  }

  name:string="";
  constructor(
    private route:Router,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.name=this.activatedRoute.snapshot.params['name'];
  }

}
