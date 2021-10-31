import { Injectable } from '@angular/core';

import { ComponentMappingTable } from './component-mapping-table';

@Injectable({ providedIn: 'root' })
export class ComponentRegisterFactoryService {
  public componentMapping: ComponentMappingTable = new ComponentMappingTable();

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
