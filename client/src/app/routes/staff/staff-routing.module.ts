import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { StaffManageComponent } from "./staff-manage/staff-manage.component";

@NgModule({
    imports: [RouterModule.forChild([{
        path: "staff-manage", component: StaffManageComponent
    }])],
    exports: [RouterModule]
})
export class StaffRoutingModule {

}