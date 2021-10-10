import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from "@angular/core";
import { StaffControllerService } from "src/shared/api";
@Component({ selector: "add-staff-model", template: './add-staff-modal.component.html' })
export class AddStaffModelComponent {

    constructor(private staffControllerService: StaffControllerService) { }

}