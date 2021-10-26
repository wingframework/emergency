import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as toml from 'toml';

enum UiType {
  AntDeisgn = 'AntDeisgn',
  Amis = 'Amis'
}
@Component({ selector: 'toml-sample', templateUrl: './toml-samples.component.html', styleUrls: ['./toml-samples.component.css'] })
export class TomlSamplesComponent {
  /**支持的控件列表 */

  selectedNode?: { title: string; link: string };
  nodes: any;
  toml!: string;
  json!: string;
  title!: string;
  body?: any;
  UiType = UiType;
  uiType: UiType = UiType.AntDeisgn;
  constructor(private activeRoute: ActivatedRoute, private http: HttpClient) {
    this.activeRoute.params.subscribe(rtn => {
      console.log(location.hash);
    });
  }
  ngOnInit() {
    this.http.get('/assets/sample/list.toml', { responseType: 'text' }).subscribe(rtn => {
      let data = toml.parse(rtn);
      console.log(data);
      this.nodes = [data];
      debugger;
    });
  }
  selectNode(node: { origin: { title: string; link: string } }) {
    this.selectedNode = node.origin;
    this.title = node.origin.title;
    this.http.get(node.origin.link, { responseType: 'text' }).subscribe(rtn => {
      this.toml = rtn;
      this.json = toml.parse(rtn);
      this.body = this.json;
    });
  }
  copyText(value: string) {
    var oInput = document.createElement('textarea');
    document.body.appendChild(oInput);
    oInput.value = value;
    oInput.select(); // 选择对象
    document.execCommand('Copy'); // 执行浏览器复制命令
  }
  refreshHighlight() {
    hljs.highlightAll();
  }
}
