import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { ViewportScroller } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    // this.anchor='',
    private router: Router,
    private vps: ViewportScroller

  ) { }


  ngOnInit(): void {

  }

  redirectSignIn() {
    this.router.navigate(['/login']);
  }

  @Input('anchor') anchor: string='';

  scrollToAnchor(){
    console.log(this.anchor);
    this.vps.scrollToAnchor(this.anchor);
  }

}
