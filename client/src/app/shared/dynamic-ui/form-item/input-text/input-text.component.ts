import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NzSizeLDSType } from 'ng-zorro-antd/core/types';

import { InputText } from '../../typings';

@Component({
  selector: 'input-text',
  templateUrl: './input-text.component.html'
})
export class InputTextComponent implements OnInit {
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
