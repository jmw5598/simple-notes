import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';

import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
  imports: [RouterModule],
  exports: [NavbarComponent],
  declarations: [NavbarComponent],
  providers: []
})
export class NavigationModule {}
