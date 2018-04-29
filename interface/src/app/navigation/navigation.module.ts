import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';

import { NavbarComponent } from './navbar/navbar.component';
import { NavbarSideComponent } from './navbar-side/navbar-side.component';
import { NavbarSideService } from './navbar-side/navbar-side.service';
import { NavigationComponent } from './navigation.component';

@NgModule({
  imports: [RouterModule, CommonModule],
  exports: [NavigationComponent],
  declarations: [NavbarComponent, NavbarSideComponent, NavigationComponent],
  providers: [NavbarSideService]
})
export class NavigationModule {}
