import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {
  constructor(
    private router: Router) {
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    const status=sessionStorage.getItem('isLoggedIn'); // or false get you logged in status from state  
    if (status=="true") {
      return true;
    }
    this.router.navigate(["login"]);
    return false;
  }

}
