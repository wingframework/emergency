import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { DynamicUiModule } from 'src/app/shared/dynamic-ui/dynamic-ui.module';

import { DynamicDemoRoutingModule } from './dynamic-demo-routing.module';
import { SampleCaseComponent } from './sample-case/sample-case.component';
import { Sample1Component } from './sample1/sample1.component';
import { AmisSampleComponent } from './toml-samples/amis-sample/amis-sample.component';
import { TomlCaseMenuComponent } from './toml-samples/toml-case-menu/toml-case-menu.component';
import { TomlSamplesComponent } from './toml-samples/toml-samples.component';

@NgModule({
  imports: [SharedModule, DynamicDemoRoutingModule, DynamicUiModule, CommonModule],
  declarations: [Sample1Component, SampleCaseComponent, TomlSamplesComponent, TomlCaseMenuComponent, AmisSampleComponent]
})
export class DynamicDemoModule {}
