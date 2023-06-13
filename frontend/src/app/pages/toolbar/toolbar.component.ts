import { Component } from '@angular/core';
import {AuthService} from "../../shared/auth.service";
import {ToolbarService} from "./toolbar.service";
import {Contact} from "../../model/model";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
})
export class ToolbarComponent {
  contacts: Contact[] = [];

  constructor(private authService: AuthService,
              private toolbarService: ToolbarService) {
    toolbarService.getContacts().subscribe(contacts => {
      this.contacts = contacts;
console.log(contacts);
    });
  }

  logout() {
    this.authService.logout();
  }
}
