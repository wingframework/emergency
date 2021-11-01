import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NzSizeLDSType } from 'ng-zorro-antd/core/types';

import { DynamicFormControlService } from '../../../core/dynamic-form-control.service';

@Component({ selector: 'amis-input-text', templateUrl: './input-text.component.html' })
export class InputTextComponent {
  @Input() formGroup!: FormGroup;
  @Input() required?: boolean;
  @Input() label?: string | false;
  @Input() labelRemark?: any;
  @Input() name!: string;
  @Input() size!: NzSizeLDSType | 'lg' | 'md' | 'sm';
  @Input() disabled?: boolean;
  constructor(private dynamicFormControlService: DynamicFormControlService) {}

  ngOnInit(): void {
    switch (this.size) {
      case 'lg':
        this.size = 'large';
        break;
      case 'md':
        this.size = 'default';
        break;
      case 'sm':
        this.size = 'small';
        break;
    }
  }

  get isValid() {
    debugger;
    return this.formGroup.controls[this.name].valid;
  }
  getLabelContent() {
    return this.dynamicFormControlService.renderTooltip(this.labelRemark.content, this.formGroup.getRawValue());
  }
}
