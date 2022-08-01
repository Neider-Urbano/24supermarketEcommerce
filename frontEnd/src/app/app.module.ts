import { MatButtonModule } from '@angular/material/button';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './publicLayout/login/login.component';
import { RegisterComponent } from './publicLayout/register/register.component';
import { Error404Component } from './publicLayout/error404/error404.component';
import { PrivatelayoutModule } from "./privateLayout/privatelayout.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmdialogComponent } from './privateLayout/components/confirmdialog/confirmdialog.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    Error404Component,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    PrivatelayoutModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    MatDialogModule 
  ],
  entryComponents:[ConfirmdialogComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
