import { Component, inject } from '@angular/core';
import { Course } from '../model/course';
import { Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { Lesson } from '../model/lesson';

@Component({
  selector: 'app-teacher-course',
  templateUrl: './teacher-course.component.html',
  styleUrls: ['./teacher-course.component.css'],
})
export class TeacherCourseComponent {
  private home: HomeComponent = inject(HomeComponent);

  course!: Course;
  lessons!: Lesson[];

  constructor(private router: Router) {
    const courseData = localStorage.getItem('course');
    if (courseData) {
      this.course = JSON.parse(courseData);
    } else {
      this.home.popBar();
      this.home.navigatePage('/home/teacher');
    }

    this.setLessons();
  }

  setLessons() {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
    };

    fetch(
      'http://localhost:8030/lessons/course/' + this.course.id,
      requestOptions
    )
      .then((response) => response.text())
      .then((result) => {
        this.lessons = JSON.parse(result);
      })
      .catch((error) => console.log('error', error));
  }

  deleteLesson(lesson: Lesson) {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var requestOptions = {
      method: 'DELETE',
      headers: myHeaders,
    };

    fetch('http://localhost:8030/lessons/' + lesson.id, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        this.setLessons();
      })
      .catch((error) => console.log('error', error));
  }

  editLesson(lesson: Lesson) {
    let person = prompt('Please enter lesson name:', lesson.name);

    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var raw = JSON.stringify({
      name: person,
      courseID: lesson.courseID,
    });

    var requestOptions = {
      method: 'PUT',
      headers: myHeaders,
      body: raw,
    };

    fetch('http://localhost:8030/lessons/' + lesson.id, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        this.setLessons();
      })
      .catch((error) => console.log('error', error));
  }

  addLesson() {
    let person = prompt('Please enter lesson name:', '');

    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var raw = JSON.stringify({
      name: person,
      courseID: this.course.id,
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
    };

    fetch('http://localhost:8030/lessons', requestOptions)
      .then((response) => response.text())
      .then((result) => console.log(result))
      .catch((error) => console.log('error', error));
    this.setLessons();
  }
}
