import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NzSizeLDSType } from 'ng-zorro-antd/core/types';
import { DynamicFormControlService } from '../../../core/dynamic-form-control.service';

@Component({ selector: 'input-number', templateUrl: './input-number.component.html' })
export class InputNumberComponent {
  @Input() formGroup!: FormGroup;
  @Input() required?: boolean;
  @Input() label?: string | false;
  @Input() labelRemark?: any;
  @Input() name!: string;
  @Input() size!: NzSizeLDSType | 'lg' | 'md' | 'sm';
  @Input() disabled!: boolean;
  @Input() disabledOn!: string;
  @Input() hiddenOn!: string;
  @Input() value: any;
  @Input() description!: string;
  @Input() placeholder: string = '';
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
    if (this.disabledOn) {
      this.formGroup.valueChanges.subscribe(r => {
        this.disabled = this.dynamicFormControlService.condistionOn(this.formGroup.getRawValue(), this.disabledOn);
      });
    }
    if (this.hiddenOn) {
      this.formGroup.valueChanges.subscribe(r => {
        this.disabled = this.dynamicFormControlService.condistionOn(this.formGroup.getRawValue(), this.hiddenOn);
      });
    }
    if (this.value) {
      this.formGroup.controls[this.name].setValue(this.value);
    }
  }

  get isValid() {
    debugger;
    return this.formGroup.controls[this.name].valid;
  }
  getLabelContent() {
    let formValue = this.formGroup.getRawValue();
    let result = this.dynamicFormControlService.renderTooltip(this.labelRemark.content, formValue);
    return result;
  }
}
