import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const appRoutes: Routes = [
  {
    path: 'topics',
    loadChildren: 'app/topics/topics.module#TopicsModule'
  },
  {
    path: 'dashboard',
    redirectTo: 'topics',
    pathMatch: 'full'
  },
  {
    path: '',
    redirectTo: 'topics',
    pathMatch: 'full'
  }

];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
