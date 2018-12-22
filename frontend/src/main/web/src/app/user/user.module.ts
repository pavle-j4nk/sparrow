import {NgModule} from '@angular/core';
import {UserProfileComponent} from "./user.profile.component";
import {CommonModule} from "@angular/common";
import {UserRoutingModule} from "./user.routing.module";
import {UserService} from "./user.service";

@NgModule({
  declarations: [
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ],
  providers: [UserService],
})
export class AppModule { }
