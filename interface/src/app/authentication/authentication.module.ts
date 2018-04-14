import { NgModule } from '@angular/core';

import { AuthenticationGuard } from './guards/authentication.guard';
import { AuthenticationService } from './services/authentication.service';

@NgModule({
  imports: [],
  exports: [],
  declarations: [],
  providers: [
    AuthenticationGuard,
    AuthenticationService
  ]
})
export class AuthenticationModule {}
