import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { DynamicFormControlService } from '../core/dynamic-form-control.service';
import { Com, CommonCom, Form } from '../typings';

@Component({ selector: 'meta-form', templateUrl: './form.component.html', styleUrls: ['./form.component.css'] })
export class FormComponent {
  @Input() body!: Form;
  @Input() formGroup!: FormGroup;

  constructor(private dynamicFormControlService: DynamicFormControlService) {}
  ngOnInit(): void {
    this.formGroup = this.dynamicFormControlService.toFormGroup(this.body.body as any);
  }
}
