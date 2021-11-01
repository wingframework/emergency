import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { FormItem } from '../typings';

@Injectable()
export class DynamicFormControlService {
  toFormGroup(formItems: FormItem[]): FormGroup {
    const group: any = {};
    formItems.forEach(item => {
      //   if (item.required) {
      group[item.name] = item.required ? new FormControl(item.value, [Validators.required]) : new FormControl(item.value, []);
      //   }
    });
    return new FormGroup(group);
  }
  renderTooltip(content: string, formValue: any = {}) {
    let result = content;
    Object.keys(formValue).forEach(key => {
      let tabKey = '${' + key + '}';
      result = result.replace(tabKey, formValue[key] ? formValue[key] : '');
      debugger;
    });
    return result;
  }

  condistionOn(value: any, exp: string) {
    function conditionDisabeld(express: string) {
      return eval(express);
    }
    return conditionDisabeld.call(value, exp);
  }
}
