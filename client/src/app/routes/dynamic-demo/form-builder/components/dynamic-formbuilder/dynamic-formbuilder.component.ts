import { Component, ComponentFactoryResolver, Input, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ComponentRegisterFactoryService } from 'dist/ng-alain-amis/lib/core/omponent-register-factory.service';
import { DynamicDirective } from 'ng-alain-amis';
import { CommonCom } from 'ng-alain-amis';
import { FormBuilderComponentFactoryService } from '../../../form-builder-component-factory.service';
import { DynamicFormBuilderDirective } from './dynamic-formbuilder.directive';

@Component({
  selector: 'dynamic-formbuilder',
  templateUrl: './dynamic-formbuilder.component.html',
  styleUrls: ['./dynamic-formbuilder.component.css']
})
export class DynamicFormBuilderComponent {
  @Input() body!: CommonCom;
  currentAdIndex = -1;
  @ViewChild(DynamicFormBuilderDirective, { static: true }) dynamic!: DynamicFormBuilderDirective;
  interval: any;
  @Input() formGroup!: FormGroup;

  constructor(
    private componentFactoryResolver: ComponentFactoryResolver,
    private formBuilderComponentFactoryService: FormBuilderComponentFactoryService
  ) {}

  ngOnInit() {
    this.loadComponent();
  }

  ngOnDestroy() {}

  loadComponent() {
    debugger;
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(
      this.formBuilderComponentFactoryService.getComponentByName(this.body.type) as any
    );

    const viewContainerRef = this.dynamic.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent<any>(componentFactory);
    Object.assign(componentRef.instance, this.body);
    if (this.formGroup) {
      componentRef.instance.formGroup = this.formGroup;
    }
  }
}
