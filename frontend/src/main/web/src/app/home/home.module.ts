import {NgModule} from "@angular/core";
import {HomeRoutingModule} from "./home.routing.module";
import {HomeComponent} from "./home.component";
import {HomeService} from "./home.service";
import {NavbarModule} from "../navbar/navbar.module";

@NgModule({
  imports: [
    HomeRoutingModule,
    NavbarModule
  ],
  declarations: [
    HomeComponent
  ],
  providers: [ HomeService]
})
export class HomeModule {
  
}
