import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { TopicsService } from './services/topics.service';

@NgModule({
  imports: [HttpModule],
  exports: [],
  declarations: [],
  providers: [
    TopicsService
  ]
})
export class CoreModule {}
