import { NzFormLayoutType } from 'ng-zorro-antd/form';

import { Com, CommonCom } from './com';

export interface Form extends Com {
  type: 'form';
  mode?: NzFormLayoutType;
  body?: CommonCom[];
  title?: string;
}
