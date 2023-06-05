import {Injectable} from '@angular/core';
import {Observable, tap} from "rxjs";
import {Token, UserDto, UserEntity} from "../../model/model";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../shared/auth.service";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http: HttpClient,
              private authService: AuthService) {
    authService.loginEventEmitter.emit(authService.isLoggedIn());
  }

  login(name: string, password: string): Observable<Token> {
    const body: UserDto = {
      chatEntities: undefined,
      userName: name,
      password: password
    }

    return this.http.post<Token>(environment.apiPath + '/login', body)
      .pipe(
        tap({
          next: res => {
            this.setSession(res)
            this.authService.loginEventEmitter.emit(this.authService.isLoggedIn());
          }
        }),
      );
  }


  private setSession(res: Token) {
    let decodedAccessToken = this.authService.decodedAccessToken(res.access_token);
    localStorage.setItem('access_token', res.access_token);
  }
}
