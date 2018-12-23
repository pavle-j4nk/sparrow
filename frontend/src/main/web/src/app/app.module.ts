import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routing.module";
import {AppComponent} from "./app.component";
import {LoginModule} from "./login/login.module";
import {HomeModule} from "./home/home.module";
import {NavbarComponent} from "./navbar/navbar.component";
import {HotelsComponent} from "./hotels/hotels.component";
import {UserModule} from "./user/user.module";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HotelsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    LoginModule,
    HomeModule,

    UserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
