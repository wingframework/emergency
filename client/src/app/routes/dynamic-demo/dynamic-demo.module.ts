import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { NgAlainAmisModule } from 'ng-alain-amis';
import { DragulaModule } from 'ng2-dragula';
import { MarkdownModule } from 'ngx-markdown';
import { DynamicUiModule } from 'src/app/shared/dynamic-ui/dynamic-ui.module';

import { DynamicDemoRoutingModule } from './dynamic-demo-routing.module';
import { DynamicFormBuilderComponent } from './form-builder/components/dynamic-formbuilder/dynamic-formbuilder.component';
import { DynamicFormBuilderDirective } from './form-builder/components/dynamic-formbuilder/dynamic-formbuilder.directive';
import { FbInputNumberComponent } from './form-builder/components/fb-input-number/fb-input-number.component';
import { FbInputPasswordComponent } from './form-builder/components/fb-input-password/fb-input-password.component';
import { FbInputTextComponent } from './form-builder/components/fb-input-text/fb-input-text.component';
import { FormBuilderContentComponent } from './form-builder/form-builder-content/form-builder-content.component';
import { FormBuilderOptionSidebarComponent } from './form-builder/form-builder-option-sidebar/form-builder-option-sidebar.component';
import { FormBuilderComponent } from './form-builder/form-builder.component';
import { ToolbarComponent } from './form-builder/toolbar/toolbar.component';
import { SampleCaseComponent } from './sample-case/sample-case.component';
import { Sample1Component } from './sample1/sample1.component';
import { AmisSampleComponent } from './toml-samples/amis-sample/amis-sample.component';
import { ComponentTree } from './toml-samples/component-tree/component-tree.component';
import { DynamicRouteSamplesComponent } from './toml-samples/dynamic-route-samples/dynamic-route-samples.component';
import { TomlCaseMenuComponent } from './toml-samples/toml-case-menu/toml-case-menu.component';
import { TomlSamplesComponent } from './toml-samples/toml-samples.component';

@NgModule({
  imports: [DragulaModule, SharedModule, MarkdownModule.forChild(), DynamicDemoRoutingModule, CommonModule, NgAlainAmisModule],
  declarations: [
    FbInputNumberComponent,
    FbInputTextComponent,
    FbInputPasswordComponent,

    DynamicFormBuilderDirective,
    DynamicFormBuilderComponent,
    FbInputTextComponent,
    FormBuilderOptionSidebarComponent,
    FormBuilderContentComponent,
    ToolbarComponent,
    FormBuilderComponent,
    Sample1Component,
    SampleCaseComponent,
    DynamicRouteSamplesComponent,
    TomlSamplesComponent,
    TomlCaseMenuComponent,
    AmisSampleComponent,
    ComponentTree
  ]
})
export class DynamicDemoModule {}
