import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Import FormsModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { TeacherService } from './service/teacher.service';
import { TeacherComponent } from './teacher/teacher.component';
import { TeacherCourseComponent } from './teacher-course/teacher-course.component';
import { CourseCreateComponent } from './course-create/course-create.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, TeacherComponent, TeacherCourseComponent, CourseCreateComponent],
  imports: [BrowserModule, FormsModule, AppRoutingModule],
  providers: [TeacherService],
  bootstrap: [AppComponent],
})
export class AppModule {}
