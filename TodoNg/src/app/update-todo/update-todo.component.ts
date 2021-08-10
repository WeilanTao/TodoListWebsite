import { UpdateTodoPayload } from './../databoject/update-todo.payload';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-update-todo',
  templateUrl: './update-todo.component.html',
  styleUrls: ['./update-todo.component.css']
})
export class UpdateTodoComponent implements OnInit {

  updatetodoform = new FormGroup({
    
      'todoname': new FormControl('', [
        Validators.maxLength(20),
        Validators.required

      ]),
      'description': new FormControl('', [
        Validators.email,
        Validators.required
      ]),
      'tododate': new FormControl('', [
        Validators.required,
        // Validators.date
      ])
  
  })

  get todoname(){
    return this.updatetodoform.get('todoname');
  }

  get description(){
    return this.updatetodoform.get('description');
  }

  get tododate(){
    return this.updatetodoform.get('date');
  }

  setTodoName(n:string){
    this.updatetodoform.get('todoname')?.setValue(n);
  }

  setDescription(d:string){
    this.updatetodoform.get('description')?.setValue(d);
  }

  setTododate(d:Date){
    this.updatetodoform.get('date')?.setValue(d);
  }

  todo: UpdateTodoPayload = {
    todoId: -1,
    todoName: "",
    description: "",
    date: new Date(Date.now()),
    isDone: false

  };

  constructor(@Inject(MAT_DIALOG_DATA) public data: UpdateTodoPayload) {
    this.todo.todoId= data.todoId;
    this.todo.todoName=data.todoName;
    this.todo.description=data.description;
    this.todo.date=data.date;
    this.todo.isDone=data.isDone;

    this.setTodoName(data.todoName);
    this.setDescription(data.todoName);
    this.setTododate(data.date);
  }

  ngOnInit(): void {
  }

  update(){
    
  }

}