import {EventEmitter, Injectable, Output} from '@angular/core';
import jwt_decode from "jwt-decode";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loginEventEmitter: EventEmitter<boolean> = new EventEmitter();

  constructor(private router: Router ) { }

  public isLoggedIn() {
    const access_token = localStorage.getItem('access_token')
    return (access_token !== null && access_token !== undefined);
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

  public decodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return undefined;
    }
  }

  public getJWT(): string | undefined {
    const token = localStorage.getItem('access_token');
    return token ? token : undefined;
  }

  public getDecodedAccessToken(): string | undefined {
    const token = localStorage.getItem('access_token');
    const decodedToken = this.decodedAccessToken(token ? token : '');
    return decodedToken ? decodedToken : undefined;
  }

  public getUserName(): string | undefined {
    if (this.isLoggedOut()) return;
    const token = localStorage.getItem('access_token');
    const decodedToken = this.decodedAccessToken(token ? token : '');
    return decodedToken ? decodedToken.upn : undefined;
  }

  logout() {
    localStorage.removeItem('access_token');
    this.loginEventEmitter.emit(this.isLoggedIn());
    this.router.navigate(['/login']);
  }
}
