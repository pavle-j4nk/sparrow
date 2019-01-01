import {NgModule} from "@angular/core";
import {HotelsRoutingModule} from "./hotels.routing.module";
import {HotelsComponent} from "./hotels.component";
import {HotelDetailsComponent} from "../hotel_details/hotel.details.component";
import {CommonModule} from "@angular/common";
import {HotelsService} from "./hotels.service";
import {HotelReservationComponent} from "../reservation_hotel/reservation.hotel.component";


@NgModule({
  imports: [
    CommonModule,
    HotelsRoutingModule
  ],
  declarations: [
    HotelsComponent,
    HotelDetailsComponent,
    HotelReservationComponent
  ],
  providers: [
    HotelsService
  ]
})
export class HotelsModule {

}
