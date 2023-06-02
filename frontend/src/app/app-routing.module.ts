import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessageComponent} from "./message/message.component";
import {LoginComponent} from "./pages/login/login.component";
import {AddfriendComponent} from "./pages/addfriend/addfriend.component";

const routes: Routes = [
  {path: 'message', component: MessageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'addfriend', component: AddfriendComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
