import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TodoNg';

  constructor(){
  }

  anchortransfer:string="";

  receiveMessage(a:string) {
    console.log("parenttransfer", a);
    this.anchortransfer = a;
  }
}
