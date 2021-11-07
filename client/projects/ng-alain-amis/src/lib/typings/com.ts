import { Form, FormItem, InputText, Page } from '.';

// type ComExtend=? extends Com;
export interface Com {
  type: string;
  body?: CommonCom[] | CommonCom;
}

export type CommonCom = Form | FormItem | Page | InputText;
