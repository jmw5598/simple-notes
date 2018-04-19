import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import { Section } from '../../shared/model/section.model';
import { Topic } from '../../shared/model/topic.model';

@Injectable()
export class TopicsService {

  private base: string = "http://localhost:8080/v1/topics/"

  constructor(private http: Http) {}

  findAllTopics(): Observable<Topic[]> {
    return this.http.get(this.base)
      .map(d => this.extractData(d))
      .catch(e => this.handleError(e));
  }

  findById(id: number): Observable<Topic> {
    return this.http.get(this.base + id)
      .map(d => {
        return this.extractData(d).topic;
      })
      .catch(e => this.handleError(e));
  }

  create(topic: Topic): Observable<Topic> {
    return this.http.post(this.base, topic)
      .map(d => this.extractData(d).topic)
      .catch(e => this.handleError(e));
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.base + id)
      .map(d => this.extractData(d))
      .catch(e => this.handleError(e))
  }

  findSectionById(topicId: number, sectionId: number): Observable<Section> {
    return this.http.get(this.base + topicId + "/sections/" + sectionId)
      .map(d => this.extractData(d))
      .catch(e => this.handleError(e));
  }

  createSection(topicId: number, section: Section): Observable<Section> {
    return this.http.post(this.base + topicId + "/sections/", section)
      .map(d => this.extractData(d))
      .catch(e => this.handleError(e));
  }

  updateSection(
    topicId: number, sectionId: number, section: Section): Observable<Section> {
    return this.http.put(this.base + topicId + "/sections/" + sectionId, section)
      .map(d => this.extractData(d))
      .catch(e => this.handleError(e));
  }

  deleteSection(topicId: number, sectionId: number): Observable<any> {
    return this.http.delete(this.base + topicId + "/sections/" + sectionId)
      .map(d => this.extractData(d))
      .catch(e => this.handleError(e));
  }

  protected extractData(res: Response) {
    let body = res.json() || '';
    return body;
  }

  protected handleError(error: Response | any) {
    let msg: string;
    if(error instanceof Response) {
      msg = error.json() || '';
    } else {
      msg = error.message ? error.message : error.toString();
    }
    return Observable.throw(msg);
  }

}
