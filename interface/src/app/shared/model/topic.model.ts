import { Section } from './section.model';

export class Topic {
  constructor(
    id: number,
    title: string,
    synopsis: string,
    createdOn: Date,
    lastModified: Date,
    sections: Section[]
  ) {}
}
