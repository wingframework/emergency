import { Injectable } from '@angular/core';
@Injectable({ providedIn: 'root' })
export class TreeUtilService {
  /**列表成树 */
  arrayToTree(
    items: any[],
    opt: { idKey?: string; parentIdKey?: string; titleKey?: string } = { idKey: 'id', parentIdKey: 'parentId', titleKey: 'string' }
  ) {
    opt.idKey = opt.idKey || 'id';
    opt.parentIdKey = opt.parentIdKey || 'parentId';
    opt.titleKey = opt.titleKey || 'title';

    let topMenuList = this.getRootMenus(items, opt as any);
    topMenuList.forEach(top => (top.children = this.getChildren(top, items, opt as any)));

    return topMenuList;
  }
  /** 递归迭代子类 */
  public getChildren(topItem: any, options: any[], opt: { idKey: string; parentIdKey: string; titleKey: string }) {
    let children = options.filter(menu => menu[opt.parentIdKey] == topItem[opt.idKey]);
    for (let submenu of children) {
      submenu.children = this.getChildren(submenu, options, opt);
    }
    return children;
  }
  /**
   * 查找顶层节点,支持非整树数据加载也可以查找出树节点
   *
   * 例如数据库有一组菜单
   * [
   *
   * {id:1,text:'系统管理',parentId:0},
   *
   * {id:11,text:'角色管理',parentId:1},
   *
   * {id:111,text:'用户管理',parentId:11}]
   *
   * 支持全部加载成树
   *
   * 也支持   [{id:11,text:'角色管理',parentId:1},{id:111,text:'用户管理',parentId:11}] 形式加载成树
   */
  getRootMenus(items: any[], opt: { idKey: string; parentIdKey: string; titleKey: string }) {
    return items.filter(menu => !items.find(item => menu[opt.parentIdKey] == item[opt.idKey]));
  }
  /**遍历子节点 */
  enumChildren(item: any, callback: Function) {
    callback(item);
    if (item.children) {
      item.children.forEach((child: any) => this.enumChildren(child, callback));
    }
  }
}
