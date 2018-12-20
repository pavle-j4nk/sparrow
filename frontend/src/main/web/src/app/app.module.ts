import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routing.module";
import {AppComponent} from "./app.component";
import {LoginModule} from "./login/login.module";
import {HomeModule} from "./home/home.module";
import {NavbarModule} from "./navbar/navbar.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HomeModule,
    NavbarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
