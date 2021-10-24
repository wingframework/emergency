import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';

import { DevRoutingModule } from './dev-routings.module';
import { EditFieldModalComponent } from './pages/meta-design-page/edit-field-modal/edit-field-modal.component';
import { MetaDesignPageComponent } from './pages/meta-design-page/meta-design-page.component';

@NgModule({ imports: [SharedModule, DevRoutingModule], declarations: [MetaDesignPageComponent, EditFieldModalComponent] })
export class DevModule {}
