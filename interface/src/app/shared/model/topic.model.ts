import { Section } from './section.model';

export class Topic {

  public id: number;
  public title: string;
  public synopsis: string;
  public createdOn: Date;
  public lastModified: Date;
  public sections: Section[];

  constructor(
    id: number,
    title: string,
    synopsis: string,
    createdOn: Date,
    lastModified: Date,
    sections?: Section[]
  ) {
    this.id = id;
    this.title = title;
    this.synopsis = synopsis;
    this.createdOn = createdOn;
    this.lastModified = lastModified;
    this.sections = this.sections;
  }

}
