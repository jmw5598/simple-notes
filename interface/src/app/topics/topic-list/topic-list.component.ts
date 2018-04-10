import { Component, OnInit, Input } from '@angular/core';

import { Topic } from '../../shared/model/topic.model';

@Component({
  selector: 'sn-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {

  @Input()
  topics: Topic[];

  constructor() { }

  ngOnInit() {
  }

}
