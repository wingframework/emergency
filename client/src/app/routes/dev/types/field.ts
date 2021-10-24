/**
 * queryTemplate
 * 元数据
 */
export class Field {
  tooltip?: string;
  type?: string;
  required?: boolean;
  fieldType?: string;

  placeholder?: string;
  /**删格布局 24列 */
  grid?: { total: number; label: number };

  /** 是否懒加载 */
  isLazy?: boolean;
  // resetTo?: (val, obj) => any;

  /**分组选项 */
  // groupOptions?: GroupOptions;
  isPk?: boolean;
  // summarys?: ISummary[];
  id?: number;
  objectCode?: string;
  // type?: FieldType;
  dynamicComponent?: any;
  sort?: 'DESC' | 'ASC';
  options?: Array<{ alias: string; value: any }>;
  /**
   * 能力
   * 位操作来附加能力,简化操作
   * power = Show|Query|Update|Create
   */
  power?: number | Function;
  isQuery?: boolean;
  isShow?: boolean;
  isUpdate?: boolean;
  // config?: FieldConfig;
  fieldName?: string;
  recno?: number;
  alias?: string;
  dynamicHtml?: string;
  presetValue?: string;
  displayWidth?: number;
  // metaObject?: MetaCom;
  readonly?: boolean;
  isRef?: boolean;
  // valid?: Valid;
  // transform?: Transform
  oneToOneUpdateOnly?: boolean;
  canFilter?: boolean;
  filterOptions?: Array<{ text: string; value: string; byDefault: boolean }>;
}
