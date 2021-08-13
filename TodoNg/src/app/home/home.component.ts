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


  scrollToAnchor(anchor:string){
    this.vps.scrollToAnchor(anchor);
  }

  goToLink(url:string){
    console.log("hihihi", url);
    window.open(url,"_blank");
  }

}
