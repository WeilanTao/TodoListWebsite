import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  adminapprove(email: string) {

    let params = new HttpParams();
    params = params.append("useremail", email);
    return this.http.put("http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/admin/adminapprove", params, { responseType: 'text' });

  }

  adminremove(email: string) {

    let params = new HttpParams();
    params = params.append("useremail", email);
    return this.http.put("http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/admin/adminremove", params, { responseType: 'text' });

  }

  getAllUsers(){
    return this.http.get("http://ec2-35-182-185-7.ca-central-1.compute.amazonaws.com:8080/admin/getalluser")
  }

}
