import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(
    @Inject(String) private url:string,
    private http:HttpClient,
  ) { }

  getAll(){
    // let basicAuthHeader= this.createBasicAuthenticationHttpHeader();
    // let header= new HttpHeaders({
    //   Authorization:basicAuthHeader
    // });

    let param= new HttpParams();
    param= param.append("userId", 1);

    const httpOptions = {
      // headers: { 'Content-Type': 'application/json' },
      // headers:header,
      params: param
    };
    return this.http.get(this.url,httpOptions);
  }

  deleteById(id:number){
    let param= new HttpParams();
    param=param.append("todoId", id);
    return this.http.delete(this.url, {params: param});
  }

  // createBasicAuthenticationHttpHeader(){
  //   let username= "emily";
  //   let password=  "nimda";

  //   let basicAuthHeader= 'Basic '+window.btoa(username+":"+password);

  //   return basicAuthHeader;

  // }
}
