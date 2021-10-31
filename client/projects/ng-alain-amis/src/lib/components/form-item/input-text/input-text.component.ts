import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NzSizeLDSType } from 'ng-zorro-antd/core/types';

@Component({ selector: 'amis-input-text', templateUrl: './input-text.component.html' })
export class InputTextComponent {
  @Input() body!: any;
  @Input() formGroup!: FormGroup;
  @Input() required?: boolean;
  @Input() label?: string | false;
  @Input() labelRemark?: string;
  @Input() name!: string;
  @Input() size!: NzSizeLDSType;
  @Input() disabled?: boolean;
  ngOnInit(): void {
    switch (this.body.size) {
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
}
