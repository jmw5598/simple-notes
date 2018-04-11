import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthenticationService } from '../core/services/authentication.service';

@Component({
  selector: 'sn-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService) {}

  ngOnInit() {

  }

  login() {
    this.authenticationService.login();
  }

}
