import { Com, CommonCom } from './com';

export interface FormItem extends Com {
  body?: CommonCom[];
  name: string;
  label?: string | boolean;
  required?: boolean;
  value?: any;
  size?: 'xs' | 'sm' | 'md' | 'lg' | 'full';
  placeholder?: string;
  labelRemark?: string | any;
  disabled?: boolean;
}

export interface InputText extends FormItem {
  type: 'input-text';
}
