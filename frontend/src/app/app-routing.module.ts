import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessageComponent} from "./message/message.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: 'message', component: MessageComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
