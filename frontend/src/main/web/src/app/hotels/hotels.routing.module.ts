import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HotelDetailsComponent} from "../hotel_details/hotel.details.component";
import {HotelsComponent} from "./hotels.component";

const routes : Routes = [
  {path: 'hotels', component : HotelsComponent},
  {path: 'hotels/details/:id', component : HotelDetailsComponent}
]

@NgModule({
  imports : [RouterModule.forChild(routes)],
  exports : [RouterModule]
})
export class HotelsRoutingModule{

}
