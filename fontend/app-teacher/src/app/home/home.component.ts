import { Component } from '@angular/core';
import { Teacher } from '../model/teacher';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  private teacher!: Teacher;

  constructor(private router: Router) {
    const teacherData = localStorage.getItem('teacher');

    if (teacherData) {
      this.teacher = JSON.parse(teacherData);
    } else {
      this.router.navigate(['/']);
    }
  }
}
