import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {

  error: string = null;

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.error = this.route.snapshot.queryParamMap.get('error');
  }


}
