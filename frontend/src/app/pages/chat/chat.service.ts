import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MessageDto} from "../../model/model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient) { }

    sendMessage(message: string, chatId: number) {
      let body: MessageDto = {
        chatEntity: undefined,
        messageId: undefined,
        messageText: message,
        messageTime: undefined,
        userEntity: undefined

      }
      return this.http.post(environment.apiPath + "/chat/" + chatId, body);
    }

    getAllMessages(chatId: number) {
      return this.http.get<MessageDto[]>(environment.apiPath + "/chat/" + chatId);
    }



}
