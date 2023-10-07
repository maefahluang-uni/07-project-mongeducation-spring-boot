import { Component, Input, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TeacherService } from '../service/teacher.service';
import { HomeComponent } from '../home/home.component';
import { Course } from '../model/course';
import { CategoryService } from '../service/category.service';
import { Category } from '../model/category';
import { find } from 'rxjs';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css'],
})
export class TeacherComponent {
  blur!: boolean;
  private url = '';
  courses: Course[] = [];
  categories: Category[] = [];

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
    this.setCategory();
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

  setCategory() {
    this.cate
      .getCategories()
      .then((response) => response.text())
      .then((result) => {
        this.categories = JSON.parse(result);
      })
      .catch((error) => console.log('error', error));
  }

  getCategoryName(courseID: string) {
    const name = this.categories.find(
      (category) => category.id == Number(courseID)
    );
    return name?.name;
  }

  getTeacherName() {
    return this.home.getTeacher().userName;
  }

  getTeacherID() {
    return this.home.getTeacher().id;
  }

  addCourse() {
    this.blur = true;
    localStorage.setItem('blur', 'true');
    this.home.navigatePage('/home/teacher/create');
  }
}
