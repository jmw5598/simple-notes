import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TopicsRoutingModule } from './topics.routing';

import { TopicDashboardComponent } from './topic-dashboard/topic-dashboard.component';
import { TopicCreateComponent } from './topic-create/topic-create.component';

@NgModule({
  imports: [
    CommonModule,
    TopicsRoutingModule
  ],
  exports: [TopicCreateComponent, TopicDashboardComponent],
  declarations: [TopicCreateComponent, TopicDashboardComponent],
  providers: []
})
export class TopicsModule {}
