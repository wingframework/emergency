import { Component, OnDestroy } from '@angular/core';
import { DragulaService } from 'ng2-dragula';

@Component({ selector: 'form-toolbar', templateUrl: './toolbar.component.html', styleUrls: ['./toolbar.component.css'] })
export class ToolbarComponent implements OnDestroy {
  constructor(private dragulaService: DragulaService) {
    dragulaService.createGroup('form-item', {
      copy: (el, source) => {
        return source.id === 'left';
      },
      accepts: (el, target: any, source, sibling) => {
        // To avoid dragging from right to left container
        // debugger;
        return target.id !== 'left';
      },
      copyItem: (person: any) => {
        debugger;
        return person.component;
      }
    });
  }
  ngOnDestroy(): void {
    this.dragulaService.destroy('form-item');
  }
  groups = [
    {
      title: '基础字段',
      code: 'basic',
      controls: [
        {
          icon: 'icon-input',
          text: '单行文本',
          component: {
            type: 'input-text',
            label: '单行文本',
            placeholder: '单行文本',
            name: 'text1'
          }
        },
        {
          icon: 'icon-input',
          text: '单行数字',
          component: {
            type: 'input-number',
            label: '单行数字',
            placeholder: '单行数字',
            name: 'number1'
          }
        },
        {
          icon: 'icon-input',
          text: '密码输入框',
          component: {
            type: 'input-password',
            label: '密码输入框',
            placeholder: '密码输入框',
            name: 'password1'
          }
        },
        {
          icon: 'icon-input',
          text: '多行文本',
          component: {
            type: 'input-number'
          }
        },
        {
          icon: 'icon-input',
          text: '下拉选择',
          component: {
            type: 'input-number'
          }
        },
        {
          icon: 'icon-input',
          text: 'checkbox',
          component: {
            type: 'input-number'
          }
        },
        {
          icon: 'icon-input',
          text: 'radio',
          component: {
            type: 'input-number'
          }
        }
      ]
    }
  ];
}
