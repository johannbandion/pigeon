import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessageComponent} from "./message/message.component";
import {AppComponent} from "./app.component";

const routes: Routes = [
  {path: 'message', component: MessageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
