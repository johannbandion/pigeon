import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-single-message',
  templateUrl: './single-message.component.html',
  styleUrls: ['./single-message.component.scss']
})
export class SingleMessageComponent {
  @Input() message: string | undefined;
  @Input() isFriendMessage: boolean | undefined;
  @Input() time: string | undefined;

  constructor() {}
}
