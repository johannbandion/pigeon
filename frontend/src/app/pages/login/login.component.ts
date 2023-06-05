import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "./login.service";
import {AuthService} from "../../shared/auth.service";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  invalidLogin: boolean = false;

  constructor(private fb: FormBuilder,
              private router: Router,
              private loginService: LoginService,
              private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      // email: ['', Validators.required],
      // password: ['', Validators.required]
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  login() {
    const loginFormValue = this.loginForm.value;

    if (loginFormValue.email && loginFormValue.password) {
      this.loginService.login(loginFormValue.email, loginFormValue.password)
        .subscribe({
            next: (_) => {
              let redirectUrl = '/';
              this.router.navigateByUrl(redirectUrl);
            },
            error: (error) => {
              this.invalidLogin = true;
            }
          }
        );
    }
  }

  signup() {
    console.log("Signup")
  }
}
