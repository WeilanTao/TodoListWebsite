import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardAdminService implements CanActivate {

  constructor(
    private authService: AuthService,
    private router:Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if( !this.authService.isUserLoggedIn() )  {
      this.router.navigate(["/login"], {queryParams:{returnUrl:state.url}});
      return false;
    }

    if (this.authService.isUserLoggedIn()  && this.authService.getAuthenticatedUserRole()==="ADMIN")
      return true;

      this.router.navigate(['/403forbidden']);
      return false;
  
  }

}
