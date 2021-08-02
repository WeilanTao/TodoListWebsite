import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterService implements HttpInterceptor{

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let username= "emily";
    let password=  "nimda";

    let basicAuthHeader= 'Basic '+window.btoa(username+':'+password);

    req= req.clone({
      setHeaders:{
        Authorization:basicAuthHeader
      }
    })
    return next.handle(req);
    // return basicAuthHeader;
    // throw new Error('Method not implemented.');
  }

}
