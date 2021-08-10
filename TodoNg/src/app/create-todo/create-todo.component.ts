import { TodoService } from './../service/todo.service';
import { CreateTodoPayload } from './../databoject/createTodo.payload';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { Moment } from 'moment';
import * as moment from 'moment';
import { GlobalVariable } from 'src/globalconstant';


@Component({
  selector: 'app-create-todo',
  templateUrl: './create-todo.component.html',
  styleUrls: ['./create-todo.component.css'],

})
export class CreateTodoComponent implements OnInit {
  createTodoForm: FormGroup;
  createTodoPayload: CreateTodoPayload;
  color: ThemePalette = 'accent';
  checked = false;
  disabled = false;
  minDate: Moment;
  maxDate: Moment;
  todonameLimit:number;
  descriptionLimit:number;


  constructor(
    private todoService: TodoService
  ) {

    this.todonameLimit = GlobalVariable.TODONAME_LIMIT;
    this.descriptionLimit = GlobalVariable.DESCRIPTION_LIMIT;


    this.createTodoForm = new FormGroup({
      'todoname': new FormControl('', [Validators.required, Validators.maxLength(this.todonameLimit)]),
      'description': new FormControl('', [Validators.maxLength(this.descriptionLimit)]),
      'tododate': new FormControl('',[Validators.required]),
      'isDone': new FormControl('')
    })

    this.setisDone(false);

    this.createTodoPayload = {
      name: '',
      description: '',
      isDone: false,
      date: new Date(),
    }
    const currentYear = moment().year();
    const currentMonth = moment().month();
    const currentDay = moment().date();
    this.minDate = moment([currentYear, currentMonth, currentDay]);
    this.maxDate = moment([currentYear + 30, 11, 31]);
  }

  ngOnInit(): void {
  }

  cancel() {
    this.createTodoForm.reset();
  }

  submit() {
    this.createTodoPayload.name = this.createTodoForm.get('todoname')?.value as string;
    this.createTodoPayload.description = this.createTodoForm.get('description')?.value as string;
    this.createTodoPayload.isDone = this.createTodoForm.get('isDone')?.value as boolean;
    this.createTodoPayload.date = this.createTodoForm.get('tododate')?.value as Date;


    this.todoService.createTodo(this.createTodoPayload)
      .subscribe(
        response => {
          console.log(response);
        }, error => {
          this.createTodoForm.reset();
        }
      )

  }

  get todoname() {
    return this.createTodoForm.get('todoname');
  }
  get description() {
    return this.createTodoForm.get('description');
  }

  get tododate() {
    return this.createTodoForm.get('tododate');
  }

  get isDone() {
    return this.createTodoForm.get('isDone');
  }

  setisDone(isdone:boolean) {
    this.createTodoForm.get('isDone')?.setValue(isdone);
  }

  settodoname(name:string) {
    this.createTodoForm.get('todoname')?.setValue(name);
  }
  setdescription(d:string) {
    this.createTodoForm.get('description')?.setValue(d);
  }

  settododate(d:Date) {
    return this.createTodoForm.get('tododate')?.setValue(d);
  }



}
