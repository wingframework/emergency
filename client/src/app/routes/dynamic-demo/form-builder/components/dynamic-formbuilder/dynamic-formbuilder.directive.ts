import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[dynamic-form-builder]'
})
export class DynamicFormBuilderDirective {
  constructor(public viewContainerRef: ViewContainerRef) {}
}
