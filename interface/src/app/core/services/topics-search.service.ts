import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { Category } from '../../shared/model/category.model';
import { Topic } from '../../shared/model/topic.model';
import { TopicsService } from './topics.service';

@Injectable()
export class TopicsSearchService {

  private categoriesSubject = new BehaviorSubject<Category[]>(null);
  private resultsSubject = new BehaviorSubject<Topic[]>(null);

  public categories = this.categoriesSubject.asObservable();
  public results = this.resultsSubject.asObservable();

  constructor(private topicsService: TopicsService) { }

  search(categories: Category[]) {
    this.topicsService.search(categories)
      .subscribe(
        data => {
          this.categoriesSubject.next(categories);
          this.resultsSubject.next(data);
        },
        error => console.log("error searching for categories inside topics search service")
      )
  }

}
