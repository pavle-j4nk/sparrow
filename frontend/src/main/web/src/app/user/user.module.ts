import {NgModule} from '@angular/core';
import {UserProfileEditorComponent} from "./profile/user.profile-editor.component";
import {CommonModule} from "@angular/common";
import {UserRoutingModule} from "./user.routing.module";
import {UserService} from "./user.service";
import {FormsModule} from "@angular/forms";
import {UserComponent} from "./user.component";
import {UserFriendComponent} from "./friends/user.friends.component";
import {AddFriendsComponent} from "./friends/add-friends.component";
import {FriendshipRequestsComponent} from "./friends/friendship-requests.component";

@NgModule({
  declarations: [
    UserComponent,
    UserProfileEditorComponent,
    UserFriendComponent,
    AddFriendsComponent,
    FriendshipRequestsComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ],
  providers: [UserService],
})
export class UserModule { }
