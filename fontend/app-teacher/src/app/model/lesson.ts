export class Lesson {
  id: number;
  name: string;
  courseID: string;

  constructor(id: number, name: string, courseID: string) {
    this.id = id;
    this.name = name;
    this.courseID = courseID;
  }
}
