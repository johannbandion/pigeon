import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  invalidLogin: boolean = false;

  constructor(private fb: FormBuilder,
              // private authService: AuthService,
              private router: Router,
              // private navbarService: ToolbarService
  ) {
    this.loginForm = this.fb.group({
      // email: ['', Validators.required],
      // password: ['', Validators.required]
      email: [''],
      password: ['']
    });
  }

  ngOnInit(): void {

  }

  login() {
    console.log("Login")
  }

  signup() {
    console.log("Signup")
  }
}
