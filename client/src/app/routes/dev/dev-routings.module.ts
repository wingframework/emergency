import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MetaDesignPageComponent } from './pages/meta-design-page/meta-design-page.component';

const routes: Routes = [{ path: 'meta-design', component: MetaDesignPageComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevRoutingModule {}
