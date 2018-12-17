import {Component} from "@angular/core";
import {HomeService} from "./home.service";

@Component({
  selector: 'home',
  templateUrl : './home.component.html'
})

export class HomeComponent {
  constructor(private homeService: HomeService) {

  }

}
