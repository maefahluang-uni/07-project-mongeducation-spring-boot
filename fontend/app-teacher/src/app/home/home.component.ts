import { Component, inject } from '@angular/core';
import { Teacher } from '../model/teacher';
import { Router } from '@angular/router';
import { TeacherService } from '../service/teacher.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  private teacherService: TeacherService = inject(TeacherService);
  private teacher!: Teacher;

  private bar: string[][] = [];

  constructor(private router: Router) {
    const teacherData = localStorage.getItem('teacher');
    if (teacherData) {
      this.teacher = JSON.parse(teacherData);
    } else {
      this.navigatePage('/');
    }

    const barData = localStorage.getItem('bar');
    if (barData) {
      this.bar = JSON.parse(barData);
    } else {
      this.pushBar(['หน้าหลัก', '/home/teacher']);
    }
  }

  getTeacher() {
    return this.teacher;
  }

  getBar() {
    return this.bar;
  }

  setBar(bar: string[][]) {}

  pushBar(itemBar: string[]) {
    this.bar.push(itemBar);
    localStorage.setItem('bar', JSON.stringify(this.getBar()));
  }

  popBar() {
    this.bar.pop();
    localStorage.setItem('bar', JSON.stringify(this.getBar()));
  }

  navigatePage(page: string) {
    this.router.navigate([page]);
  }

  clickBar(item: string[]) {
    // Find the index of the clicked item in the this.bar array
    const index = this.bar.findIndex((barItem) => barItem === item);

    if (index !== -1) {
      // Use splice to remove all items after the clicked item
      this.bar.splice(index + 1);

      // Save the updated this.bar array in local storage
      localStorage.setItem('bar', JSON.stringify(this.getBar()));

      // Navigate to the selected page
      this.navigatePage(item[1]);
    }
  }

  submitSearchForm() {}

  logout() {
    localStorage.removeItem('teacher')
    this.navigatePage('/')
  }
}
