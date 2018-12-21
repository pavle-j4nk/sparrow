import {Component, OnInit} from "@angular/core";
import {HOTELS} from "../mock-hotels";

@Component({
  selector : 'hotels',
  templateUrl : './hotels.component.html'
})
export class HotelsComponent implements OnInit {

  hotels = HOTELS;

  constructor() {}

  ngOnInit(): void {
  }
}
