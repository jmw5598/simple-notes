import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';

import { NavbarComponent } from './navbar/navbar.component';
import { NavbarSideComponent } from './navbar-side/navbar-side.component';
import { NavigationComponent } from './navigation.component';

@NgModule({
  imports: [RouterModule],
  exports: [NavigationComponent],
  declarations: [NavbarComponent, NavbarSideComponent, NavigationComponent],
  providers: []
})
export class NavigationModule {}
