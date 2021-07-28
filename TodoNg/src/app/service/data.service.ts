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
    let param= new HttpParams();
    param= param.append("userId", 1);
    return this.http.get(this.url,  {params: param});
  }

  deleteById(id:number){
    let param= new HttpParams();
    param=param.append("todoId", id);
    return this.http.delete(this.url, {params: param});
  }
}
