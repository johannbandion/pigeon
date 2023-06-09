import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessageComponent} from "./pages/message/message.component";
import {LoginComponent} from "./pages/login/login.component";
import {AddfriendComponent} from "./pages/addfriend/addfriend.component";
import {AuthGuard} from "./core/auth.guard";
import {ChatComponent} from "./pages/chat/chat.component";

const routes: Routes = [
  {path: '', component: AddfriendComponent, pathMatch: 'full', canActivate: [AuthGuard]},
  {path: 'message', component: MessageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'addfriend', component: AddfriendComponent,  canActivate: [AuthGuard]},
  {path: 'chat/:chatId', component: ChatComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
