import { CommonCom } from '.';
import { Com } from './com';

export interface Page extends Com {
  type: 'page';
  body: CommonCom;
  title?: string;
}
