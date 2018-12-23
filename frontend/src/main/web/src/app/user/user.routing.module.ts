import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserProfileComponent} from "./user.profile.component";

const routes: Routes = [
  { path: 'user/profile/:id',  component: UserProfileComponent },
  { path: 'user/profile',  component: UserProfileComponent },
  { path: 'user/profile/edit',  component: UserProfileComponent }
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
