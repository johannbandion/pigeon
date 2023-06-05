import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from "../shared/auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const access_token: string | undefined  = this.authService.getJWT();
    console.log(this.authService.getDecodedAccessToken())
    if (this.authService.isLoggedIn() && access_token) {
      const clonedRequest = request.clone({
        headers: request.headers.set("Authorization", "Bearer " + access_token)
      })
      return next.handle(clonedRequest);
    }
    return next.handle(request);
  }
}
