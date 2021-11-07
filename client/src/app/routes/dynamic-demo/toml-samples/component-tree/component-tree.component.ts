import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { CodeCompileService } from '../services/code-compile.service';
import * as toml from 'toml';
import * as path from 'path-browserify';
import { HttpClient } from '@angular/common/http';

class FileResult {
  constructor(public filename: string, public content: string) {}
  get fileType() {
    switch (this.filename.split('.').pop()) {
      case 'ts':
        return 'typescript';
      case 'html':
        return 'html';
      case 'css':
        return 'css';
      default:
        return 'html';
    }
  }
}

interface Control {
  name: string;
  type: 'html' | 'ts' | 'css';
  path: string;
  content?: string;
  abs_path: string;
}

@Component({ selector: 'component-tree', templateUrl: './component-tree.component.html' })
export class ComponentTree implements OnInit, AfterViewInit {
  constructor(private http: HttpClient) {}
  ngAfterViewInit(): void {
    this.compile();
  }
  @Input() json: any;
  nodes: any[] = [];
  controls: Control[] = [];
  files: FileResult[] = [];

  ngOnInit() {
    this.json = this.deepClone(this.json);
    this.getTreeNode(this.json);
    this.nodes = [this.json];
    Handlebars.registerHelper('isFalse', function (value: any) {
      return value === false;
    });
    Handlebars.registerHelper('isNotFalse', function (value: any) {
      return value !== false;
    });
  }
  getTreeNode(item: any) {
    item.children = item.body;
    item.title = item.type;
    // item.expand = true;
    debugger;
    // delete item.body;

    if (item.children) {
      if (!Array.isArray(item.children)) {
        item.children = [item.children];
      }
      item.children.forEach((child: any) => this.getTreeNode(child));
    } else {
      item.isLeaf = true;
    }
  }
  refreshHighlight() {
    hljs.highlightAll();
  }

  async loadToml(url: string) {
    let tomlText = await this.http.get(url, { responseType: 'text' }).toPromise();
    return toml.parse(tomlText);
  }

  codeRender(data: any): FileResult[] {
    let result: FileResult[] = [];
    let controls = this.controls.filter(t => t.name == data.type);
    controls.forEach(c => {
      let template = Handlebars.compile(c.content as string);
      let content = template(data);
      result.push(new FileResult(c.name + '.' + c.type, content));
    });
    return result;
  }
  async compile() {
    let base = '/assets/templates';
    let url = base + `/template.toml`;
    let templates = await this.loadToml(url);
    this.controls = [];
    Handlebars.registerHelper('dynamic', function (context: any, options: any) {
      debugger;
      if (context.hash.body.expand) {
        return context.hash.body.type + '.' + context.hash.file;
      } else {
        return 'dynamic-ui';
      }
    });

    Handlebars.registerHelper('attribute', function (context: any, options: any) {
      debugger;
      let keys = Object.keys(context);
      return keys
        .filter(k => k !== 'children')
        .map(k => (k == 'body' ? `[body]="body"` : `[${k}]="${context[k]}"`))
        .join(' ');
    });

    Handlebars.registerPartial(
      `dynamic-ui`,
      `
    <{{body.type}} {{#attribute  body}} {{/attribute}} ></{{body.type}}>
    `
    );

    for (let item of templates.templates) {
      let controlPath = path.resolve(base, item.path);
      let control = await this.loadToml(controlPath);
      for (let template of control.templates) {
        let controlDir = controlPath.replace(path.basename(controlPath), '');
        let templatePath = path.resolve(controlDir, template.path);
        let controlTemplate = await this.loadTemplateText(templatePath);
        // debugger;
        Handlebars.registerPartial(template.name + '.' + template.type, controlTemplate);
        this.controls.push({
          name: template.name,
          type: template.type,
          path: template.path,
          content: controlTemplate,
          abs_path: templatePath
        });
      }
    }
    this.files = await this.codeRender(this.json);
    setTimeout(() => {
      hljs.highlightAll();
    }, 2000);
  }

  async loadTemplateText(url: string) {
    return await this.http.get(url, { responseType: 'text' }).toPromise();
  }
  deepClone(o: any) {
    // 判断如果不是引用类型，直接返回数据即可
    if (typeof o === 'string' || typeof o === 'number' || typeof o === 'boolean' || typeof o === 'undefined') {
      return o;
    } else if (Array.isArray(o)) {
      // 如果是数组，则定义一个新数组，完成复制后返回
      // 注意，这里判断数组不能用typeof，因为typeof Array 返回的是object
      console.log(typeof []); // --> object
      var _arr: any = [];
      o.forEach(item => {
        _arr.push(item);
      });
      return _arr;
    } else if (typeof o === 'object') {
      var _o: any = {};
      for (let key in o) {
        _o[key] = this.deepClone(o[key]);
      }
      return _o;
    }
  }

  copyText(value: string) {
    var oInput = document.createElement('textarea');
    document.body.appendChild(oInput);
    oInput.value = value;
    oInput.select(); // 选择对象
    document.execCommand('Copy'); // 执行浏览器复制命令
  }
}
