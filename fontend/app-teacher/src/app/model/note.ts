export class Note {
  id: number;
  name: string;
  noteSource: string;
  lessonID: string;

  constructor(id: number, name: string, noteSource: string, lessonID: string) {
    this.id = id;
    this.name = name;
    this.noteSource = noteSource;
    this.lessonID = lessonID;
  }
}
