import { Component, Input, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TeacherService } from '../service/teacher.service';
import { HomeComponent } from '../home/home.component';
import { Course } from '../model/course';
import { CategoryService } from '../service/category.service';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css'],
})
export class TeacherComponent {
  blur!: boolean;
  private url = '';
  courses: Course[] = [];

  private home: HomeComponent = inject(HomeComponent);
  private cate: CategoryService = inject(CategoryService);

  constructor() {
    const b = localStorage.getItem('blur');
    if (b == 'true') {
      this.blur = Boolean(b);
    } else {
      this.blur = false;
    }

    this.url = `http://localhost:8020/courses/teacher/${
      this.home.getTeacher().id
    }`;
    this.setCourses();
  }

  goCourse(course: Course) {
    localStorage.setItem('course', JSON.stringify(course));
    this.home.pushBar([course.name, '/home/course']);
    this.home.navigatePage('/home/course');
  }

  setCourses() {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
    };

    fetch(this.url, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        this.courses = JSON.parse(result);
      })
      .catch((error) => console.log('error', error));
  }

  getCategory(id: string) {
    this.cate
      .getCategoryById(id)
      .then((response) => response.text())
      .then((result) => {
        let resultData = JSON.parse(result);
        return resultData.name;
      })
      .catch((error) => console.log('error', error));
  }

  getTeacherName() {
    return this.home.getTeacher().userName;
  }

  addCourse() {
    this.blur = true;
    localStorage.setItem('blur', 'true');
    this.home.navigatePage('/home/teacher/create');
  }
}
