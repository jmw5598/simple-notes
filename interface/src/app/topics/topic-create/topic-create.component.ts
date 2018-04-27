import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { Permission } from '../../shared/model/permission.enum';
import { Topic } from '../../shared/model/topic.model';
import { TopicsService } from '../../core/services/topics.service';

@Component({
  selector: 'sn-topic-create',
  templateUrl: './topic-create.component.html',
  styleUrls: ['./topic-create.component.css']
})
export class TopicCreateComponent implements OnInit {

  permission = Permission;
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private topicsService: TopicsService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      title: ['', [Validators.required]],
      synopsis: ['', [Validators.required]],
      permission: [Permission.PRIVATE, [Validators.required]]
    });
  }

  submit(topic: Topic) {
    this.topicsService.create(topic)
      .subscribe(
        data => this.router.navigate(['../', data.topic.id], { relativeTo: this.route }),
        error => console.log("error saving new topic " + error)
      );
  }

}
