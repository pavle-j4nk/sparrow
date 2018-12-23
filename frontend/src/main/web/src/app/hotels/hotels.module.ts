import {NgModule} from "@angular/core";
import {HotelsRoutingModule} from "./hotels.routing.module";
import {HotelsComponent} from "./hotels.component";
import {HotelDetailsComponent} from "../hotel_details/hotel.details.component";
import {CommonModule} from "@angular/common";


@NgModule({
  imports: [
    CommonModule,
    HotelsRoutingModule
  ],
  declarations: [
    HotelsComponent,
    HotelDetailsComponent
  ]
})
export class HotelsModule {

}
