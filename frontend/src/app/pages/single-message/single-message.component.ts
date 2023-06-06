import {Component} from '@angular/core';

@Component({
  selector: 'app-single-message',
  templateUrl: './single-message.component.html',
  styleUrls: ['./single-message.component.scss']
})
export class SingleMessageComponent{
  isFriendMessage: boolean = true;
  messageText: string = "";

  constructor() {
    this.isFriendMessage = true; // Set this based on your logic
    this.messageText = "message"; // Set the variable text based on your requirements
  }
}
