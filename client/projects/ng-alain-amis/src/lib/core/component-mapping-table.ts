import { InputNumberComponent } from '../components/form-item/input-numer/input-number.component';
import { InputPasswordComponent } from '../components/form-item/input-password/input-password.component';
import { InputTextComponent } from '../components/form-item/input-text/input-text.component';
import { FormComponent } from '../components/form/form.component';
import { PageComponent } from '../components/page/page.component';

export interface IMappingTable {
  [key: string]: Function;
}

/**组件映射表 */
export class ComponentMappingTable {
  public table: Map<string, Function> = new Map<string, Function>();

  constructor() {}
}
