import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Sample1Component } from './sample1/sample1.component';
import { TomlSamplesComponent } from './toml-samples/toml-samples.component';

const routes: Routes = [
  { path: '', redirectTo: 'sample1', pathMatch: 'full' },
  { path: 'sample1', component: Sample1Component },
  { path: 'toml-sample', component: TomlSamplesComponent }
  //   { path: 'analysis', component: DashboardAnalysisComponent },
  //   { path: 'monitor', component: DashboardMonitorComponent },
  //   { path: 'workplace', component: DashboardWorkplaceComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DynamicDemoRoutingModule {}
