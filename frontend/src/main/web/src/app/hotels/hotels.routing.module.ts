import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HotelDetailsComponent} from "../hotel_details/hotel.details.component";
import {HotelsComponent} from "./hotels.component";
import {HotelReservationComponent} from "../reservation_hotel/reservation.hotel.component";

const routes : Routes = [
  {path: 'hotels', component : HotelsComponent},
  {path: 'hotels/details/:id', component : HotelDetailsComponent},
  {path: 'hotels/reservation', component : HotelReservationComponent} //TODO: promijeni putanju: hotels/{id}/reserve
]

@NgModule({
  imports : [RouterModule.forChild(routes)],
  exports : [RouterModule]
})
export class HotelsRoutingModule{

}
