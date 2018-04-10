import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'sn-topic-create',
  templateUrl: './topic-create.component.html',
  styleUrls: ['./topic-create.component.css']
})
export class TopicCreateComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    console.log("new topic form");
  }

}
