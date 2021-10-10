import { NgModule } from "@angular/core";
import { SharedModule } from "@shared";
import { AddStaffModelComponent } from "./staff-manage/add-staff-modal/add-staff-modal.component";
import { StaffManageComponent } from "./staff-manage/staff-manage.component";
import { StaffRoutingModule } from "./staff-routing.module";

@NgModule({
    declarations: [StaffManageComponent, AddStaffModelComponent],
    imports: [SharedModule, StaffRoutingModule]
})
export class StaffModule {

}