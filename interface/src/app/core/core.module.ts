import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AuthenticationService } from './services/authentication.service';
import { TopicsService } from './services/topics.service';

@NgModule({
  imports: [HttpModule],
  exports: [],
  declarations: [],
  providers: [
    AuthenticationService,
    TopicsService
  ]
})
export class CoreModule {}
