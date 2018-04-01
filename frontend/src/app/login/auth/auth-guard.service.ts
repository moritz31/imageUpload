import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AppService } from '../../app.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public auth: AppService, public router: Router) {}

  canActivate(): boolean {
    if (!this.auth.isAuthenticated) {
      console.log("Service - Not authenticated");
      this.router.navigate(['login']);
      return false;
    }
    console.log(this.auth.authenticated)
    console.log("Service - Authenticated");
    return true;
  }

}
