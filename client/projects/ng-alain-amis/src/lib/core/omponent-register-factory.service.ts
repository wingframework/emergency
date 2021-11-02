import { Injectable } from '@angular/core';
import { InputNumberComponent } from '../components/form-item/input-numer/input-number.component';
import { InputPasswordComponent } from '../components/form-item/input-password/input-password.component';
import { InputTextComponent } from '../components/form-item/input-text/input-text.component';
import { FormComponent } from '../components/form/form.component';
import { PageComponent } from '../components/page/page.component';

import { ComponentMappingTable } from './component-mapping-table';

@Injectable({ providedIn: 'root' })
export class ComponentRegisterFactoryService {
  public componentMapping: ComponentMappingTable = new ComponentMappingTable();
  constructor() {
    this.componentMapping.table.set('form', FormComponent);
    this.componentMapping.table.set('page', PageComponent);
    this.componentMapping.table.set('input-text', InputTextComponent);
    this.componentMapping.table.set('input-password', InputPasswordComponent);
    this.componentMapping.table.set('input-number', InputNumberComponent);
  }
  enumChildren() {}

  public addComponentMapping(name: string, component: Function) {
    if (this.componentMapping.table.has(name)) {
      throw new Error(`component ${name} exsist`);
    }
    this.componentMapping.table.set(name, component);
  }
  public getComponentByName(name: string) {
    if (this.componentMapping.table.has(name)) {
      return this.componentMapping.table.get(name);
    } else {
      throw new Error(`component ${name} not exsist`);
    }
  }
}
