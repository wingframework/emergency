import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzCardModule } from 'ng-zorro-antd/card';
import { SHARED_DELON_MODULES } from './alain.module';
import { InputNumberComponent } from './components/form-item/input-numer/input-number.component';
import { InputPasswordComponent } from './components/form-item/input-password/input-password.component';
import { InputTextComponent } from './components/form-item/input-text/input-text.component';
import { FormComponent } from './components/form/form.component';
import { PageComponent } from './components/page/page.component';
import { DynamicFormControlService } from './core/dynamic-form-control.service';
import { DynamicDirective } from './core/dynamic.directive';
import { DynamicComponent } from './dynamic.component';
import { NgAlainAmisComponent } from './ng-alain-amis.component';
import { SHARED_ZORRO_MODULES } from './zorro-module';

const COMPONENTS = [
  NgAlainAmisComponent,
  PageComponent,
  DynamicComponent,
  FormComponent,
  InputTextComponent,
  InputNumberComponent,
  InputPasswordComponent,
  DynamicDirective,
  InputPasswordComponent
];
@NgModule({
  declarations: [...COMPONENTS],
  imports: [FormsModule, CommonModule, SHARED_DELON_MODULES, NzCardModule, ReactiveFormsModule, SHARED_ZORRO_MODULES],
  exports: [...COMPONENTS],
  providers: [DynamicFormControlService]
})
export class NgAlainAmisModule {}
