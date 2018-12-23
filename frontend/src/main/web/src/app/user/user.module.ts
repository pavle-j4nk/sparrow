import {NgModule} from '@angular/core';
import {UserProfileComponent} from "./user.profile.component";
import {CommonModule} from "@angular/common";
import {UserRoutingModule} from "./user.routing.module";
import {UserService} from "./user.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ],
  providers: [UserService],
})
export class UserModule { }
