import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuPageComponent } from './pages/menu-page/menu-page.component';
import { UserPageComponent } from './pages/user-page/user-page.component';
const routes = [
  { path: 'user', component: UserPageComponent },
  { path: 'role', component: UserPageComponent },
  { path: 'menu', component: MenuPageComponent },
  { path: 'org', component: UserPageComponent }
];

@NgModule({ imports: [RouterModule.forChild(routes)], exports: [RouterModule] })
export class RbacRoutingModule {}
