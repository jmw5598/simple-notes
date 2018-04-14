import { Component } from '@angular/core';

import { AuthenticationService } from '../../authentication/services/authentication.service';

@Component({
  selector: 'sn-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private authenticationService: AuthenticationService) {}

  logout() {
    this.authenticationService.logout();
  }

}
