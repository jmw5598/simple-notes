import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import { AuthenticationService } from './authentication/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  login = true;


  constructor(public authenticationService: AuthenticationService) {}

  ngOnInit() {
  }

  isAuthenticated() {
    let auth: boolean = this.authenticationService.isAuthenticated();
    console.log("App Auth: " + auth);
    return auth;
  }


}
