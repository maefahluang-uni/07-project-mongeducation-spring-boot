import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TeacherService } from '../service/teacher.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css'],
})
export class TeacherComponent {
  private home: HomeComponent = inject(HomeComponent);

  constructor(private router: Router) {}

  goCourse() {
    this.home.pushBar(['Coursename', '/home/course']);
    this.router.navigate(['/home/course']);
  }
}
