import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import { Section } from '../../shared/model/section.model';
import { SectionResource } from '../../shared/model/section-resource.model';

@Injectable()
export class SectionsService {

  private base: string = "http://localhost:8080/v1/topics/";

  constructor(private http: HttpClient) { }

  findAll(topicId: number): Observable<Section[]> {
    return this.http.get(this.base + topicId + "/sections")
      .map(d => d)
      .catch(e => this.handleError(e));
  }

  findById(topicId: number, sectionId: number): Observable<SectionResource> {
    return this.http.get(this.base + topicId + "/sections/" + sectionId)
      .map(d => d)
      .catch(e => this.handleError(e));
  }

  create(topicId: number, section: Section): Observable<SectionResource> {
    return this.http.post(this.base + topicId + "/sections/", section)
      .map(d => { console.log(d); return d;})
      .catch(e => this.handleError(e));
  }

  update(
    topicId: number, sectionId: number, section: Section): Observable<SectionResource> {
    return this.http.put(this.base + topicId + "/sections/" + sectionId, section)
      .map(d => { console.log(d); return d;})
      .catch(e => this.handleError(e));
  }

  delete(topicId: number, sectionId: number): Observable<any> {
    return this.http.delete(this.base + topicId + "/sections/" + sectionId)
      .map(d => d)
      .catch(e => this.handleError(e));
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
