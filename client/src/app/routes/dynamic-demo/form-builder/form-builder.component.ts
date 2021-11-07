import { Component } from '@angular/core';
import { FormBuilderComponentFactoryService } from '../form-builder-component-factory.service';
import { FbInputNumberComponent } from './components/fb-input-number/fb-input-number.component';
import { FbInputPasswordComponent } from './components/fb-input-password/fb-input-password.component';
import { FbInputTextComponent } from './components/fb-input-text/fb-input-text.component';

@Component({ selector: 'form-builder', templateUrl: './form-builder.component.html', styleUrls: ['./form-builder.component.css'] })
export class FormBuilderComponent {
  //   groups = [{ title: '基础字段', controls: [{ icon: 'icon-input', component: {} }] }];

  constructor(private formBuilderComponentFactory: FormBuilderComponentFactoryService) {
    this.formBuilderComponentFactory.addComponentMapping('input-text', FbInputTextComponent);
    this.formBuilderComponentFactory.addComponentMapping('input-number', FbInputNumberComponent);
    this.formBuilderComponentFactory.addComponentMapping('input-password', FbInputPasswordComponent);
  }
}
