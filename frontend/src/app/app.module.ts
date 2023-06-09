import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { LoginComponent } from './pages/login/login.component';
import { ToolbarComponent } from './pages/toolbar/toolbar.component';
import { MessageComponent } from './pages/message/message.component';
import { MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddfriendComponent } from './pages/addfriend/addfriend.component';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {AuthInterceptor} from "./core/auth.interceptor";
import { ChatComponent } from './pages/chat/chat.component';
import {SingleMessageComponent} from "./pages/single-message/single-message.component";
import { MatSnackBarModule } from '@angular/material/snack-bar';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ToolbarComponent,
    MessageComponent,
    LoginComponent,
    AddfriendComponent,
    ChatComponent,
    SingleMessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    MatTableModule,
    FormsModule,
    MatPaginatorModule,
    HttpClientModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
