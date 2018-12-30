import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routing.module";
import {AppComponent} from "./app.component";
import {LoginModule} from "./login/login.module";
import {HomeModule} from "./home/home.module";
import {NavbarComponent} from "./navbar/navbar.component";
import {HotelsComponent} from "./hotels/hotels.component";
import {UserModule} from "./user/user.module";
import {HttpConfigInterceptor} from "./http/HttpConfigInterceptor";
import {HTTP_INTERCEPTORS} from "@angular/common/http";

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
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
