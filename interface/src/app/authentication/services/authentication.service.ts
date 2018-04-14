import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class AuthenticationService {

  private isAuthenticated: boolean = false;
  private authenticationSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isAuthenticated);

  public authentication = this.authenticationSubject.asObservable();

  constructor() { }

  login() {
    this.isAuthenticated = true;
    this.notify();
  }

  logout() {
    this.isAuthenticated = false;
    this.notify();
  }

  private notify() {
    this.authenticationSubject.next(this.isAuthenticated);
  }

}
