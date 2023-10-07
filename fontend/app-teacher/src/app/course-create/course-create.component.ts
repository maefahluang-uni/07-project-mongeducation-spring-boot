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
  addCourse() {}
}
