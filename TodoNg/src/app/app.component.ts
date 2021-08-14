import { HomeComponent } from './home/home.component';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'TodoNg';


  activatedComponentReference: any;

  onActivate(activatedComponentReference:any) {
    this.activatedComponentReference = activatedComponentReference;
    // console.log("activatedComponentReference", );
  }

  receiveMessage(a: string) {
    // console.log("parenttransfer", a);
    //When a component is loaded, onActivate will be triggered and we will get the component reference and can access all functions using that.
    const childRouteComp = this.activatedComponentReference as HomeComponent;
    childRouteComp.scrollToAnchor(a);
  }

}
