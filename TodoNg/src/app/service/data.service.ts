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

  getAllTodos(){
    let param= new HttpParams();
    param= param.append("userId", 1);
    return this.http.get(this.url,  {params: param});

  }
}
