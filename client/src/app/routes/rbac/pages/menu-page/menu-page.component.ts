import { Component } from '@angular/core';
import { TreeUtilService } from 'src/app/shared/utils/tree-util.service';

@Component({ selector: 'menu-page', templateUrl: './menu-page.component.html' })
export class MenuPageComponent {
  constructor(private treeUtilService: TreeUtilService) {}
}
