import { Component } from '@angular/core';
import {AuthService} from "../../shared/auth.service";
import {ToolbarService} from "./toolbar.service";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
})
export class ToolbarComponent {

  constructor(private authService: AuthService,
              private toolbarService: ToolbarService) {
    toolbarService.getContacts().subscribe(res => {
      console.log("res: ",res);
    });
  }

  logout() {
    this.authService.logout();
  }
}
