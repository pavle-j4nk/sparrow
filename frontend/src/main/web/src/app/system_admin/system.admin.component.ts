import {Component} from "@angular/core";
import {Hotel} from "../hotel";
import {SystemAdminService} from "./system.admin.service";

@Component({
  selector : 'system-admin',
  templateUrl : "./system.admin.component.html"
})
export class SystemAdminComponent {
  private hotels : Hotel[];

  constructor(private systemAdminService: SystemAdminService) {
    systemAdminService.getHotels().subscribe(hotels => this.hotels = hotels);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.systemAdminService.addHotel({name} as Hotel)
      .subscribe(hotel => {
        this.hotels.push(hotel)
      });
  }
}
