import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./guards/auth-guard.service";
import {HomeComponent} from "./home/home.component";
import {NavbarComponent} from "./navbar/navbar.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [AuthGuard]},
  {path: 'home', component: HomeComponent},
  {path: 'navbar', component: NavbarComponent} //privremeno samo, u svrhe testiranja. Ukloniti kasnije
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
