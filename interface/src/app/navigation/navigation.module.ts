import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';

import { NavbarComponent } from './navbar/navbar.component';
import { NavbarSideComponent } from './navbar-side/navbar-side.component';

@NgModule({
  imports: [RouterModule],
  exports: [NavbarComponent, NavbarSideComponent],
  declarations: [NavbarComponent, NavbarSideComponent],
  providers: []
})
export class NavigationModule {}
