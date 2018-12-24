import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserProfileEditorComponent} from "./profile/user.profile-editor.component";
import {UserComponent} from "./user.component";
import {UserFriendListComponent} from "./friends/user.friends-list.component";

const routes: Routes = [
  {
    path: 'user', component: UserComponent, children: [
      {path: 'profile', component: UserProfileEditorComponent},
      {path: 'profile/:id', component: UserProfileEditorComponent},
      {path: 'friends', component: UserFriendListComponent}

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
