import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NzFormLayoutType } from 'ng-zorro-antd/form';
import { DynamicFormControlService } from '../../core/dynamic-form-control.service';

@Component({ selector: 'amis-form', templateUrl: './form.component.html', styleUrls: ['./form.component.css'] })
export class FormComponent {
  @Input() title?: string;
  @Input() mode: NzFormLayoutType = 'vertical';
  @Input() body!: any[];
  @Input() formGroup!: FormGroup;

  constructor(private dynamicFormControlService: DynamicFormControlService) {}
  ngOnInit(): void {
    this.formGroup = this.dynamicFormControlService.toFormGroup(this.body as any);
  }
}
