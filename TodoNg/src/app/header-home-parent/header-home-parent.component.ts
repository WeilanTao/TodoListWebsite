import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-home-parent',
  template: `
  <app-header (anchorChangeEvent)="receiveMessage($event)"></app-header>
  <app-home [anchor]="anchortransfer"></app-home>
`,
  styleUrls: ['./header-home-parent.component.css']
})
export class HeaderHomeParentComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("hihihi");
  }

  anchortransfer:string="";

  receiveMessage(a:string) {
    console.log("parenttransfer", a);
    this.anchortransfer = a;
  }

}
