import {ChangeDetectorRef, Component, Input} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

import {ActivatedRoute} from "@angular/router";
import {ChatService} from "./chat.service";

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

  constructor(private chatService: ChatService,
              private changeDetector: ChangeDetectorRef,
              private activatedRoute: ActivatedRoute) {

  }


  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.chatId = params['chatId'];
      this.chatId ? this.getAllMessages(this.chatId) : null;
    });
  }

  getAllMessages(chatID: number) {
    this.chatService.getAllMessages(chatID)
      .subscribe(messages => {
        console.log(messages);
      })
  }

  submitMessage() {
    let message = this.messageForm.get("message")?.value
    console.log(message);
    this.messageForm.get("message")?.setValue("");
  }

}
