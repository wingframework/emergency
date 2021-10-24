import { Component, ComponentFactoryResolver, Input, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { DynamicDirective } from './core/dynamic.directive';
import { ComponentRegisterFactoryService } from './core/omponent-register-factory.service';
import { Com, CommonCom, Page } from './typings';

@Component({ selector: 'dynamic-ui', templateUrl: './dynamic-ui.component.html' })
export class DynamicUiComponent {
  @Input() body!: CommonCom;
  currentAdIndex = -1;
  @ViewChild(DynamicDirective, { static: true }) dynamic!: DynamicDirective;
  interval: any;
  @Input() formGroup!: FormGroup;

  constructor(
    private componentFactoryResolver: ComponentFactoryResolver,
    private componentRegisterMappingFactory: ComponentRegisterFactoryService
  ) {}

  ngOnInit() {
    this.loadComponent();
    // this.getAds();
  }

  ngOnDestroy() {}

  loadComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(
      this.componentRegisterMappingFactory.getComponentByName(this.body.type) as any
    );

    const viewContainerRef = this.dynamic.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent<any>(componentFactory);
    componentRef.instance.body = this.body;
    if (this.formGroup) {
      componentRef.instance.formGroup = this.formGroup;
    }
  }
}
