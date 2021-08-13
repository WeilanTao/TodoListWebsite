import { EventEmitter } from '@angular/core';
import { Component, OnInit, Output } from '@angular/core';
import { AuthService } from '../service/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isUserLoggedIn:boolean=false;

  constructor(
     public authService:AuthService
  ) { }

 

  ngOnInit(): void {
  }


  @Output() anchorChangeEvent = new EventEmitter<string>();

  sendanchor(anchor:string){
    this.anchorChangeEvent.emit(anchor);
  }
 
  goToLink(url:string){
    console.log("hihihi", url);
    window.open(url,"_blank");
  }
}
