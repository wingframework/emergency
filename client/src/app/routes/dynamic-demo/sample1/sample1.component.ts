import { Component } from '@angular/core';
import { CommonCom, Page } from 'src/app/shared/dynamic-ui/typings';

@Component({ selector: 'sample1', templateUrl: './sample1.component.html' })
export class Sample1Component {
  sampleForm: CommonCom = {
    type: 'page',
    body: {
      title: 'sample1页面',

      type: 'form',
      mode: 'inline',
      body: [{ type: 'input-text', size: 'sm', name: 'username', label: '用户名' }]
    }
  };

  formLabelDisabeld: CommonCom = {
    type: 'page',
    body: {
      type: 'form',
      title: '禁用标签',

      mode: 'horizontal',
      body: [
        {
          type: 'input-text',
          label: '常规',
          name: 'text1',
          placeholder: '常规标签'
        },
        {
          type: 'input-text',
          label: '',
          name: 'text1',
          placeholder: '不显示标签'
        },
        {
          type: 'input-text',
          label: false,
          name: 'text1',
          placeholder: '不显示标签且清除空间'
        }
      ]
    }
  };
  formLabelRemark: CommonCom = {
    type: 'page',
    body: {
      type: 'form',
      title: '标签备注',

      mode: 'horizontal',
      body: [
        {
          type: 'input-text',
          label: '无标签提示',
          name: 'text1'
        },
        {
          type: 'input-text',
          label: '有标签提示',
          labelRemark: '这是一段提示',
          name: 'text2'
        },
        {
          type: 'input-text',
          label: '更复杂的标签提示',
          labelRemark: {
            type: 'remark',
            title: '提示',
            content: '<pre>first \nsecond\n${text1}</pre>'
          },
          name: 'text3'
        }
      ]
    }
  };

  formItemDisabled: Page = {
    type: 'page',
    body: {
      type: 'form',
      body: [
        {
          type: 'input-text',
          label: '常规',
          name: 'text1'
        },
        {
          type: 'input-text',
          label: '禁用',
          name: 'text2',
          disabled: true
        }
      ]
    }
  };
}
