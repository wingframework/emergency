import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { MenuPageComponent } from './pages/menu-page/menu-page.component';
import { UserPageComponent } from './pages/user-page/user-page.component';
import { RbacRoutingModule } from './rbac-routing.module';

@NgModule({
  declarations: [UserPageComponent, MenuPageComponent],
  imports: [SharedModule, RbacRoutingModule]
})
export class RbacModule {}
