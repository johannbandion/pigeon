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
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  login() {
    const {password, userName} = this.loginForm.value;



    if (userName && password) {
      this.loginService.login(userName, password)
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
    const {password, userName} = this.loginForm.value;

    if (userName && password) {

      this.loginService.signup(userName, userName).subscribe({
          next: (_) => {
            this.login()
          },
          error: (error) => {
            this.invalidLogin = true;
          }
        }
      );
    }
  }
}
