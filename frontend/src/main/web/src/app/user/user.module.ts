import {NgModule} from '@angular/core';
import {UserProfileEditorComponent} from "./profile/user.profile-editor.component";
import {CommonModule} from "@angular/common";
import {UserRoutingModule} from "./user.routing.module";
import {UserService} from "./user.service";
import {FormsModule} from "@angular/forms";
import {UserComponent} from "./user.component";
import {UserFriendListComponent} from "./friends/user.friends-list.component";

@NgModule({
  declarations: [
    UserComponent,
    UserProfileEditorComponent,
    UserFriendListComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ],
  providers: [UserService],
})
export class UserModule { }
