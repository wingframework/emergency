import { Component } from '@angular/core';
import { TreeUtilService } from 'src/app/shared/utils/tree-util.service';

@Component({ selector: 'user-page', templateUrl: './user-page.component.html' })
export class UserPageComponent {
  constructor(private treeUtilService: TreeUtilService) {}
  allTreeData: any;
  notAllTreeData: any;
  ngOnInit() {}
  allToTree() {
    this.allTreeData = this.treeUtilService.arrayToTree([
      { id: 1, text: '系统菜单', parentId: 0 },
      { id: 2, text: '角色权限', parentId: 1 },
      { id: 3, text: '用户菜单', parentId: 2 }
    ]);
    this.notAllTreeData = this.treeUtilService.arrayToTree([
      { id: 2, text: '角色权限', parentId: 1 },
      { id: 3, text: '用户菜单', parentId: 2 }
    ]);
  }
  logEnum() {
    this.treeUtilService.enumChildren(this.allTreeData[0], (item: any) => console.log(item));
  }
}
