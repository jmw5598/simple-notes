import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

import { Section } from '../../../shared/model/section.model';
import { TopicsService } from '../../../core/services/topics.service';

@Component({
  selector: 'sn-section-editor',
  templateUrl: './section-editor.component.html',
  styleUrls: ['./section-editor.component.css']
})
export class SectionEditorComponent implements OnInit, OnDestroy {

  notes: string = '';

  private subscription: Subscription;
  private saveTimer: any;
  public saveMessage: string = '';
  topicId: number;
  sectionId: number;
  section: Section;

  constructor(
    private route: ActivatedRoute,
    private topicsService: TopicsService
  ) { }

  ngOnInit() {
    this.subscription = this.route.params
      .subscribe(
        params => {
          this.topicId = +params['id'];
          this.sectionId = +params['sectionId'];
          this.retrieveSection();
        }
      );
  }

  save() {
    this.saveMessage = "Saving..."
    this.topicsService.updateSection(this.topicId, this.sectionId, this.section)
      .subscribe(
        data => {
          setTimeout(() => this.saveMessage = 'Saved', 1000);
          this.section = data;
        },
        error => console.log("error updating section with id: " + this.sectionId + " with topic id: " + this.topicId)
      );
  }

  // figure out a cleaner way to do this, maybe using a directive?
  autoSave() {
    clearTimeout(this.saveTimer);
    this.saveTimer = setTimeout(() => {
      this.save();
    }, 2000)
  }

  private retrieveSection() {
    this.topicsService.findSectionById(this.topicId, this.sectionId)
      .subscribe(
        data => {this.section = data; console.log(data);},
        error => console.log("error retrieving section with id: " + this.sectionId + "& topidId: " + this.topicId)
      );
  }

  ngOnDestroy() {
    if(this.subscription)
      this.subscription.unsubscribe();
  }

}
