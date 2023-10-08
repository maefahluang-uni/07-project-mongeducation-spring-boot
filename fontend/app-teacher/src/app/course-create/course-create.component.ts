import { Component, inject } from '@angular/core';
import { Category } from '../model/category';
import { CategoryService } from '../service/category.service';
import { HomeComponent } from '../home/home.component';
import { TeacherComponent } from '../teacher/teacher.component';

@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.component.html',
  styleUrls: ['./course-create.component.css'],
})
export class CourseCreateComponent {
  private home: HomeComponent = inject(HomeComponent);

  categoryService: CategoryService = inject(CategoryService);
  categories!: Category[];

  courseData = {
    name: '',
    description: ' ',
    price: 0.0,
    credit: 2,
    categoryID: '1',
  };

  errorText = '';

  constructor(private teacher: TeacherComponent) {
    this.categoryService
      .getCategories()
      .then((response) => response.text())
      .then((result) => {
        this.categories = JSON.parse(result);
        console.log(this.categories);
      })
      .catch((error) => console.log('error', error));
  }

  colse() {
    localStorage.setItem('blur', '');
    this.home.navigatePage('/home/teacher');
    this.teacher.blur = false;
  }

  addCourse() {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var raw = JSON.stringify({
      name: this.courseData.name,
      description: this.courseData.description,
      price: this.courseData.price,
      credit: this.courseData.credit,
      categoryID: this.courseData.categoryID,
      teacherID: this.teacher.getTeacherID(),
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
    };

    fetch('http://localhost:8020/courses', requestOptions)
      .then((response) => response.text())
      .then((result) => console.log(result))
      .catch((error) => console.log('error', error));
    this.colse();
    this.teacher.setCourses();
  }
}
