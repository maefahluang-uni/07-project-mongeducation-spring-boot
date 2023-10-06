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

  getBar() {
    return this.bar;
  }

  setBar(bar: string[][]) {}

  pushBar(itemBar: string[]) {
    this.bar.push(itemBar);
    console.log(JSON.stringify(this.getBar()));
    localStorage.setItem('bar', JSON.stringify(this.getBar()));
  }

  navigatePage(page: string) {
    this.router.navigate([page]);
  }

  submitSearchForm() {}
}
