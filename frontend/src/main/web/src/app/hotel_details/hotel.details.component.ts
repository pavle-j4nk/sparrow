import {Component, OnInit} from "@angular/core";
import {Hotel} from "../hotel";
import {HOTELS} from "../mock-hotels";
import {ActivatedRoute, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'hotel-details',
  templateUrl: './hotel.details.component.html'
})
export class HotelDetailsComponent implements OnInit {

  hotels = HOTELS;
  hotel: Hotel;

  constructor(activatedRoute: ActivatedRoute) {
    this.hotel = this.hotels[activatedRoute.snapshot.paramMap.get("id")];
  }

  ngOnInit(): void {
  }

}
