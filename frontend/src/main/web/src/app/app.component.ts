import {Component} from "@angular/core";
import {User} from "./user/user";
import {LoginService} from "./login/login.service";

@Component({
  selector: 'app',
  templateUrl: './app.component.html'
})

export class AppComponent {
  user : User;

  constructor (private loginService: LoginService) {
    loginService.me().subscribe(me => this.user = me);
  }

}
