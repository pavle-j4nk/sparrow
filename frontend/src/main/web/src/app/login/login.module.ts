import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import {LoginService} from "./login.service";
import {LoginComponent} from "./login.component";
import {LoginRoutingModule} from "./login.routing.module";
import {HttpClientModule} from "@angular/common/http";
import {AuthGuard} from "../guards/auth-guard.service";

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule,
    HttpClientModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [ LoginService, AuthGuard ]
})
export class LoginModule {}
