import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { TeacherComponent } from './teacher/teacher.component';
import { TeacherCourseComponent } from './teacher-course/teacher-course.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: '', redirectTo: 'teacher', pathMatch: 'full' },
      { path: 'teacher', component: TeacherComponent },
      { path: 'course', component: TeacherCourseComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
