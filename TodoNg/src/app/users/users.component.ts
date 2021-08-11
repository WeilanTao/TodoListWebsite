import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from './../service/admin/admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  username:string="";
  users:any;
  notEmpty:any;

  
  displayedColumns = ['name', 'email', 'role'];
  constructor(
    private adminService:AdminService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.username = this.activatedRoute.snapshot.params['username'];

    this.getAllUsers();
  }

  goAdminPage(){
    this.router.navigate(['adminpage', this.username,'access'])
  }

  getAllUsers(){
    this.adminService.getAllUsers()
      .subscribe(response=>{
        this.notEmpty = response.toString() === "" ? true : false;

        this.users = response;

      })
  }
}
