import {Component} from "@angular/core";
import {User} from "./user/user";
import {LoginService} from "./login/login.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app',
  templateUrl: './app.component.html'
})

export class AppComponent {
  user: User;

  constructor(private loginService: LoginService, private http: HttpClient) {
    loginService.me().subscribe(me => this.user = me);
  }

}
