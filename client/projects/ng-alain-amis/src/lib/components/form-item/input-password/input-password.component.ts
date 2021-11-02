import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NzSizeLDSType } from 'ng-zorro-antd/core/types';

@Component({ selector: 'amis-input-password', templateUrl: './input-password.component.html' })
export class InputPasswordComponent {
  @Input() body!: any;
  @Input() formGroup!: FormGroup;
  @Input() required?: boolean;
  @Input() label?: string | false;
  @Input() labelRemark?: string;
  @Input() name!: string;
  @Input() size!: NzSizeLDSType | 'lg' | 'md' | 'sm';
  @Input() disabled!: boolean;
  @Input() placeholder: string = '';
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
}
