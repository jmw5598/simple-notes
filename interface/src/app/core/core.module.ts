import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { TopicsService } from './services/topics.service';

@NgModule({
  imports: [HttpClientModule],
  exports: [],
  declarations: [],
  providers: [
    TopicsService
  ]
})
export class CoreModule {}
