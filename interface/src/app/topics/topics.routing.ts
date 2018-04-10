import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TopicDashboardComponent } from './topic-dashboard/topic-dashboard.component';
import { TopicCreateComponent } from './topic-create/topic-create.component';

const TOPIC_ROUTES = [
  {
    path: '',
    component: TopicDashboardComponent
  },
  {
    path: 'create',
    component: TopicCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(TOPIC_ROUTES)],
  exports: [RouterModule]
})
export class TopicsRoutingModule {}
