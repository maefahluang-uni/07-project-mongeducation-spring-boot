import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private url = 'http://localhost:8020/courses';

}
