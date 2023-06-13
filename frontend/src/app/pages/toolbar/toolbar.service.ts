import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Contact} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class ToolbarService {

  constructor(private http: HttpClient) { }

  getContacts() {
    return this.http.get<Contact[]>(environment.apiPath + '/toolbar/contacts')
  }
}
