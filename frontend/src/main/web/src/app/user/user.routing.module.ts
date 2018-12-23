import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserProfileComponent} from "./user.profile.component";
import {UserComponent} from "./user.component";

const routes: Routes = [
  {
    path: 'user', component: UserComponent, children: [
      {path: 'profile', component: UserProfileComponent},
      {path: 'profile/:id', component: UserProfileComponent}
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
