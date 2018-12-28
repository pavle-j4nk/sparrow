import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserProfileEditorComponent} from "./profile/user.profile-editor.component";
import {UserComponent} from "./user.component";
import {UserFriendComponent} from "./friends/user.friends.component";
import {AddFriendsComponent} from "./friends/add-friends.component";
import {FriendshipRequestsComponent} from "./friends/friendship-requests.component";

const routes: Routes = [
  {
    path: 'user', component: UserComponent, children: [
      {path: 'profile', component: UserProfileEditorComponent},
      {path: 'profile/:id', component: UserProfileEditorComponent},
      {path: 'friends', component: UserFriendComponent},
      {path: 'addFriends', component: AddFriendsComponent},
      {path: 'friendRequests', component: FriendshipRequestsComponent}

    ]
  }
];


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule {

}
