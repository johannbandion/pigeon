import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ToolbarService {

  constructor(private http: HttpClient) { }

  getContacts() {
    return this.http.get<Map<number, string>>(environment.apiPath + '/toolbar/contacts')
  }
}
