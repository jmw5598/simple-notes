import { Injectable, OnDestroy } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';

import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class AuthenticationGuard implements CanActivate, OnDestroy {

  private subscription: Subscription;
  isAuthenticated: boolean;


  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.authentication
      .subscribe(
        data => this.isAuthenticated = data,
        error => console.log("error subscibing to authencatio form auth guard")
      )
  }

  canActivate(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.isAuthenticated) {
      return this.isAuthenticated;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }

  ngOnDestroy() {
    if(this.subscription)
      this.subscription.unsubscribe();
  }

}
