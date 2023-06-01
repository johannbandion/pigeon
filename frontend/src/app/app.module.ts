import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { LoginComponent } from './pages/login/login.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MessageComponent } from './message/message.component';
import { MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ToolbarComponent,
    MessageComponent,
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
