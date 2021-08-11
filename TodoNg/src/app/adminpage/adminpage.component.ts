import { AuthService } from './../service/auth/auth.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminService } from './../service/admin/admin.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminpageComponent implements OnInit {

  adminPageForm: FormGroup;

  username:string;

  cannotRemoveAdmin: boolean = false;
  cannotAddAdmin: boolean = false;
  removeAdminNotExist = false;

  constructor(
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute,
    private router: Router 
    ) {
      this.username = this.activatedRoute.snapshot.params['username'];

    this.adminPageForm = new FormGroup({
      'approveEmail': new FormControl('', [Validators.email, Validators.required]),
      'removeEmail': new FormControl('', [Validators.email, Validators.required])
    })
  }

  ngOnInit(): void {
  }


  goTodo(){
    this.router.navigate(['user',this.username, 'todos']);
  }

  goUsers(){
    this.router.navigate(['admin',this.username,'allusers']);
  }


  //TODO:SUCCESSFULL MESSAGE
  approveAdmin() {
    console.log(this.approveEmail?.value as string);
    this.adminService.adminapprove(this.approveEmail?.value as string)
      .subscribe(response => {
        console.log(response);
        this.approveEmail?.reset();
      }, (error: Response) => {
        if (error.status === 409 || error.status === 404) {
          console.log(error);
          this.cannotAddAdmin = true;
        }
        this.approveEmail?.reset();
      }
      )
      this.cannotAddAdmin = false;
  }


  removeAdmin() {
    console.log(this.removeEmail?.value as string);
    this.adminService.adminremove(this.removeEmail?.value as string)
      .subscribe(response => {
        console.log(response);
        this.removeEmail?.reset();
      }, (error: Response) => {
        if (error.status === 409) {
          console.log(error);
          console.log(this.cannotRemoveAdmin , this.removeAdminNotExist)
          this.cannotRemoveAdmin = true;
        } else if (error.status === 404) {
          console.log(error);
          console.log(this.cannotRemoveAdmin , this.removeAdminNotExist)
          this.removeAdminNotExist = true;
        }
        this.removeEmail?.reset();
      }
      )
      this.cannotRemoveAdmin =false;
      this.removeAdminNotExist = false;
  }

  get approveEmail() {
    return this.adminPageForm.get('approveEmail');
  }
  get removeEmail() {
    return this.adminPageForm.get('removeEmail');
  }


  setremoveEmail(e: string) {
    this.adminPageForm.get('removeEmail')?.setValue(e);
  }

  setapproveEmail(e: string) {
    this.adminPageForm.get('approveEmail')?.setValue(e);
  }

}
