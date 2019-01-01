import {Component, OnInit} from "@angular/core";
import {Hotel} from "../hotel";
import {HotelsService} from "./hotels.service";

@Component({
  selector : 'hotels',
  templateUrl : './hotels.component.html'
})
export class HotelsComponent{

  hotels : Hotel[];

  constructor(private hotelsService : HotelsService) {
    hotelsService.getHotels().subscribe(hotels => this.hotels = hotels);
  }

}
