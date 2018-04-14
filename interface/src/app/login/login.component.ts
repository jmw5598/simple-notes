import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../authentication/services/authentication.service';

@Component({
  selector: 'sn-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router: Router,
    private authenticationService : AuthenticationService
  ) {}

  ngOnInit() {

  }

  login() {
    this.authenticationService.login();
    this.router.navigate(['topics']);
  }

}
