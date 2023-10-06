import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

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
    console.log('Register Data:', this.RegisterData);
  }

  submitLoginForm() {
    console.log('Login Data:', this.LoginData);
  }
}
