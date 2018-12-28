import {Component, OnInit} from "@angular/core";
import {LoginService} from "../login/login.service";
import {User} from "../user/user";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {

  me: User = null;

  constructor(private loginService: LoginService) {
    loginService.me().subscribe(me => this.me = me);
  }

  ngOnInit() {
  }

}
