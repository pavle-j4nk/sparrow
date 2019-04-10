import {Component, OnInit} from "@angular/core";
import {Hotel} from "../hotel";
import {ActivatedRoute, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'hotel-details',
  templateUrl: './hotel.details.component.html'
})
export class HotelDetailsComponent implements OnInit {

  hotel: Hotel;

  constructor(activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
  }

}
