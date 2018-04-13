import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import { AuthenticationService } from './core/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  login = true;

  public isAuthenticated: boolean;
  private authenticated: Subscription;

  constructor(private authenticationService: AuthenticationService) {}

  ngOnInit() {
    this.authenticationService.authentication
      .subscribe(
        data => this.isAuthenticated = data,
        error => console.log("error subscribing to authencation")
      );
  }

  ngOnDestroy() {
    if(this.authenticated)
      this.authenticated.unsubscribe();
  }

}
