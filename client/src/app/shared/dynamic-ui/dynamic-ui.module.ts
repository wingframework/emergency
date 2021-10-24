import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '@shared';

import { SHARED_ZORRO_MODULES } from '../shared-zorro.module';
import { DynamicFormControlService } from './core/dynamic-form-control.service';
import { DynamicDirective } from './core/dynamic.directive';
import { ComponentRegisterFactoryService } from './core/omponent-register-factory.service';
import { DynamicUiComponent } from './dynamic-ui.component';
import { FormItemComponent } from './form-item/form-item.component';
import { InputTextComponent } from './form-item/input-text/input-text.component';
import { FormComponent } from './form/form.component';
import { PageComponent } from './page/page.component';

@NgModule({
  imports: [CommonModule, SharedModule, ReactiveFormsModule, FormsModule, SHARED_ZORRO_MODULES],
  exports: [DynamicUiComponent],

  declarations: [FormComponent, FormItemComponent, DynamicUiComponent, DynamicDirective, PageComponent, InputTextComponent],
  providers: [ComponentRegisterFactoryService, DynamicFormControlService]
})
export class DynamicUiModule {}
