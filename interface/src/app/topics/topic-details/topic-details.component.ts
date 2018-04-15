import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

import { Topic } from '../../shared/model/topic.model';
import { TopicsService } from '../../core/services/topics.service';

@Component({
  selector: 'sn-topic-details',
  templateUrl: './topic-details.component.html',
  styleUrls: ['./topic-details.component.css']
})
export class TopicDetailsComponent implements OnInit, OnDestroy {

  private params: Subscription;
  private id: number;
  public topic: Topic;

  constructor(
    private route: ActivatedRoute,
    private topicsService: TopicsService
  ) { }

  ngOnInit() {
    this.params = this.route.params
      .subscribe(
        params => {
          this.id = +params['id']; // (+) converts string 'id' to a number
          this.retrieveData();
        }
      );
  }

  delete(sectionId: number) {
    this.topicsService.deleteSection(this.topic.id, sectionId)
      .subscribe(
        data => this.retrieveData(),
        error => console.log("error deleting section with id: " + sectionId)
      );
  }

  retrieveData() {
    this.topicsService.findById(this.id)
      .subscribe(
        data => this.topic = data,
        error => console.log("error getting data for id: " + this.id)
      );
  }

  ngOnDestroy() {
    if(this.params)
      this.params.unsubscribe();
  }

}
