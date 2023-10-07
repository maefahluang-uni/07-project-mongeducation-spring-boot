export class Video {
  id: number;
  name: string;
  time: string; // In TypeScript, you can represent time as a string
  videoSource: string;
  lessonID: string;

  constructor(
    id: number,
    name: string,
    time: string,
    videoSource: string,
    lessonID: string
  ) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.videoSource = videoSource;
    this.lessonID = lessonID;
  }
}
