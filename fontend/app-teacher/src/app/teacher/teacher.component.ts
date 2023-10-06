import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css'],
})
export class TeacherComponent {
  constructor(private router: Router) {}

  goCourse() {
    this.router.navigate(['/home/course']);
  }
}
