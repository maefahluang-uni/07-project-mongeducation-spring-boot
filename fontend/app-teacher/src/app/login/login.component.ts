import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Teacher } from '../model/teacher';
import { TeacherService } from '../service/teacher.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  activeTab!: string;

  LoginData = {
    username: '',
    password: '',
  };
  RegisterData = {
    firstName: '',
    lastName: '',
    username: '',
    idCard: '',
    password: '',
    repeatPassword: '',
  };

  errorText = '';

  errorTextReg = '';

  private teacher!: Teacher;

  constructor(private router: Router, private teacherService: TeacherService) {}

  ngOnInit() {
    this.activeTab = localStorage.getItem('activeTab') || 'login';
  }

  showLoginPage() {
    this.activeTab = 'login';
    this.saveActiveTabToLocalStorage();
  }

  showRegisterPage() {
    this.activeTab = 'register';
    this.saveActiveTabToLocalStorage();
  }

  private saveActiveTabToLocalStorage() {
    localStorage.setItem('activeTab', this.activeTab);
  }

  submitRegisterForm() {
    if (this.RegisterData.password !== this.RegisterData.repeatPassword) {
      this.errorTextReg = 'Password do not match';
      return;
    }
    if (this.RegisterData.password.length <= 7) {
      this.errorTextReg = 'Password must longer 8 character';
      return;
    }

    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var raw = JSON.stringify({
      firstName: this.RegisterData.firstName,
      lastName: this.RegisterData.lastName,
      idCard: this.RegisterData.idCard,
      userName: this.RegisterData.username,
      passWord: this.RegisterData.password,
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
    };

    fetch('http://localhost:8010/teachers', requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        this.errorTextReg = result;
        if (result == 'created') {
          this.showLoginPage();
        }
      })
      .catch((error) => {
        console.log('error', error);
        this.errorTextReg = error;
      });
  }

  submitLoginForm() {
    this.findTeacher();
  }

  async findTeacher() {
    var requestOptions = {
      method: 'GET',
    };

    await fetch(
      `http://localhost:8010/teachers/userName/${this.LoginData.username}`,
      requestOptions
    )
      .then((response) => response.text())
      .then((result) => {
        const res = JSON.parse(result);

        if (res.passWord != this.LoginData.password) {
          throw new Error('Failed, password not true'); // Handle non-OK responses as an error
        }
        this.teacher = new Teacher(
          res.id,
          res.bankID,
          res.firstName,
          res.lastName,
          res.idCard,
          res.userName,
          res.passWord
        );
        // Set the teacher in the service
        localStorage.setItem('teacher', JSON.stringify(this.teacher));

        // Navigate to /home
        this.router.navigate(['/home']);
      })
      .catch((error) => {
        this.errorText = '!!Username or password is false!!';
        console.log('error', error);
      });
  }
}
