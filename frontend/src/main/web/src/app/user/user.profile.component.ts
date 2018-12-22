import {Component, Input, OnInit} from "@angular/core";


@Component({
  selector: 'profile',
  templateUrl: './user.profile.component.html'
})

export class UserProfileComponent implements OnInit {
  @Input() id;

  constructor() {
  }

  ngOnInit(): void {
  }

}
