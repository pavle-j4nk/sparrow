import {NgModule} from "@angular/core";
import {NavbarRoutingModule} from "./navbar.routing.module";
import {NavbarComponent} from "./navbar.component";
import {NavbarService} from "./navbar.service";


@NgModule({
  imports: [
    NavbarRoutingModule
  ],
  declarations: [
    NavbarComponent
  ],
  providers: [ NavbarService]
})
export class NavbarModule {

}
