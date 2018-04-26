import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { SectionsService } from './services/sections.service';
import { TopicsService } from './services/topics.service';

@NgModule({
  imports: [HttpClientModule],
  exports: [],
  declarations: [],
  providers: [
    SectionsService,
    TopicsService
  ]
})
export class CoreModule {}
