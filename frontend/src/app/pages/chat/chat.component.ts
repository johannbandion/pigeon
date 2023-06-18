import {ChangeDetectorRef, Component, Input} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

import {ActivatedRoute} from "@angular/router";
import {ChatService} from "./chat.service";
import {MessageDto} from "../../model/model";
import {interval} from "rxjs";
import {AuthService} from "../../shared/auth.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  messageForm = new FormGroup({
    message: new FormControl('', [Validators.minLength(1)])
  }, {});

  chatId?: number;

  messages?: MessageDto[];

  constructor(private chatService: ChatService,
              private changeDetector: ChangeDetectorRef,
              private activatedRoute: ActivatedRoute,
              private authService: AuthService) {

  }


  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.chatId = params['chatId'];
      this.chatId ? this.getAllMessages(this.chatId) : null;
    });
    interval(1000).subscribe(() => {
      this.chatId ? this.getAllMessages(this.chatId) : null;
    });
  }

  getAllMessages(chatID: number) {
    this.chatService.getAllMessages(chatID)
      .subscribe(messages => {
        this.messages = messages;
        this.changeDetector.markForCheck();
        console.log(messages);
      })
  }

  submitMessage() {
    let message = this.messageForm.get("message")?.value
    console.log(message);
    if (message != undefined && this.chatId != undefined)
    this.chatService.sendMessage(message, this.chatId).subscribe(() => {
      ///// TODO change
      this.getAllMessages(this.chatId!);
    });
    this.messageForm.get("message")?.setValue("");

  }

  getFormatedTime(messageTime: Date | undefined):string {
    if (messageTime != undefined) {
      return ""; // messageTime.getHours() + ":" + messageTime.getMinutes();
    }
    return "";
  }

  isFriendMessage(userName: string | undefined) {
    if (userName == undefined) {
      return true;
    }
    return userName != this.authService.getUserName();
  }
}
