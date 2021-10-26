import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { NzTreeSelectComponent } from 'ng-zorro-antd/tree-select';

@Component({ selector: 'toml-case-menu', templateUrl: './toml-case-menu.component.html' })
export class TomlCaseMenuComponent {
  expandKeys = ['100', '1001'];
  @ViewChild('treeSelect') treeSelect!: NzTreeSelectComponent;
  @Output() OnSelectNode = new EventEmitter();
  value?: string;
  @Input() nodes = [];
  onChange($event: string): void {
    console.log($event);
    let node = this.treeSelect.getSelectedNodeList()[0];
    if (node) {
      this.OnSelectNode.emit(node);
    }
  }

  ngOnInit(): void {
    // mock async
  }
}
