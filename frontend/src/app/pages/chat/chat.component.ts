import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
//import {ChatService} from "./chat.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {

  //private chatService: ChatService
  constructor() {
  }
  messageForm = new FormGroup({
    message: new FormControl('', [Validators.minLength(1)])
  }, {});

  submitMessage() {
    let message = this.messageForm.get("message")?.value
    console.log(message);
    this.messageForm.get("message")?.setValue("");
  }

}
