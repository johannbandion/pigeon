import {Component} from '@angular/core';
import {AddfriendService} from "../addfriend/addfriend.service";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-single-message',
  templateUrl: './single-message.component.html',
  styleUrls: ['./single-message.component.scss']
})
export class SingleMessageComponent{
  isFriendMessage: boolean = true;
  messageText: string = "";

  constructor(private message: string, private isFriend: boolean) {
    this.isFriendMessage = isFriend; // Set this based on your logic
    this.messageText = message; // Set the variable text based on your requirements
  }
}
