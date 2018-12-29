import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {SystemAdminComponent} from "./system.admin.component";
import {SystemAdminService} from "./system.admin.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports : [
    CommonModule,
    FormsModule
  ],
  declarations : [
    SystemAdminComponent
  ],
  providers: [
    SystemAdminService
  ]
})
export class SystemAdminModule {

}
