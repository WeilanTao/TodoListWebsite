import { UpdateTodoPayload } from './../databoject/update-todo.payload';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Moment } from 'moment';
import { TodoService } from '../service/todo.service';
import * as moment from 'moment';
import { GlobalVariable } from 'src/globalconstant';

@Component({
  selector: 'app-update-todo',
  templateUrl: './update-todo.component.html',
  styleUrls: ['./update-todo.component.css']
})
export class UpdateTodoComponent implements OnInit {
  minDate: Moment;
  maxDate: Moment;
  updateTodoForm: FormGroup;
  updateTodoPayload: UpdateTodoPayload;

  todonameLimit:number;
  descriptionLimit:number;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: UpdateTodoPayload,
    private todoService: TodoService
  ) {

    console.log(data);

    this.todonameLimit = GlobalVariable.TODONAME_LIMIT;
    this.descriptionLimit = GlobalVariable.DESCRIPTION_LIMIT;

    this.updateTodoForm = new FormGroup({
      'todoname': new FormControl('', [Validators.required, Validators.maxLength(this.todonameLimit)]),
      'description': new FormControl('', [Validators.maxLength(this.descriptionLimit)]),
      'tododate': new FormControl('', [Validators.required]),
    })

    this.settodoname(data.name);
    this.setdescription(data.description);
    this.settododate(data.date);


    this.updateTodoPayload = {
      todo_id: data.todo_id,
      name: data.name,
      description: data.description,
      isDone: data.isDone,
      date: data.date
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
    this.updateTodoForm.reset();
  }

  submit(){
    this.updateTodoPayload.name = this.updateTodoForm.get('todoname')?.value as string;
    this.updateTodoPayload.description = this.updateTodoForm.get('description')?.value as string;
    this.updateTodoPayload.date = this.updateTodoForm.get('tododate')?.value as Date;

    this.todoService.updateTodo(this.updateTodoPayload)
      .subscribe(
        response => {
          console.log(response);
        }, error => {
          this.updateTodoForm.reset();
        }
      )

  }


  get todoname() {
    return this.updateTodoForm.get('todoname');
  }
  get description() {
    return this.updateTodoForm.get('description');
  }

  get tododate() {
    return this.updateTodoForm.get('tododate');
  }

  settodoname(name: string) {
    this.updateTodoForm.get('todoname')?.setValue(name);
  }
  setdescription(d: string) {
    this.updateTodoForm.get('description')?.setValue(d);
  }

  settododate(d: Date) {
    return this.updateTodoForm.get('tododate')?.setValue(d);
  }


}