import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { NgAlainAmisModule } from 'ng-alain-amis';
import { DynamicUiModule } from 'src/app/shared/dynamic-ui/dynamic-ui.module';

import { DynamicDemoRoutingModule } from './dynamic-demo-routing.module';
import { SampleCaseComponent } from './sample-case/sample-case.component';
import { Sample1Component } from './sample1/sample1.component';
import { AmisSampleComponent } from './toml-samples/amis-sample/amis-sample.component';
import { ComponentTree } from './toml-samples/component-tree/component-tree.component';
import { DynamicRouteSamplesComponent } from './toml-samples/dynamic-route-samples/dynamic-route-samples.component';
import { TomlCaseMenuComponent } from './toml-samples/toml-case-menu/toml-case-menu.component';
import { TomlSamplesComponent } from './toml-samples/toml-samples.component';

@NgModule({
  imports: [SharedModule, DynamicDemoRoutingModule, CommonModule, NgAlainAmisModule],
  declarations: [
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
