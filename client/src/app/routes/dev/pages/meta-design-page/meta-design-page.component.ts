import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { STColumn } from '@delon/abc/st';
import * as Q from 'q';
import { Field } from '../../types/field';

// import { MyHttpService } from '../../meta-ui/service/http.service';
// import { Field } from '../../meta-ui/util/meta/Field';
// import { MetaCom } from '../../meta-ui/util/meta/MetaCom';
// import { C, U, D, S, checkPower } from '../../meta-ui/util/meta/Power';
type SQLFieldType = 'VARCHAR' | 'DATETIME' | 'INT';
@Component({
  selector: 'meta-design-page',
  templateUrl: './meta-design-page.component.html',
  styleUrls: ['./meta-design-page.component.css']
})
export class MetaDesignPageComponent {
  selectedField!: Field;
  editFieldVisible: boolean = false;
  sourcePanelActive: boolean = false;
  fields: any[] = [];
  metaCom: any = {};
  columns: STColumn[] = [
    { index: 'no', type: 'no', title: '序号' },
    { index: 'fieldName', title: '字段名' },
    { index: 'alias', title: '别名' },
    { index: 'isPk', title: '是否主键', type: 'yn' },
    { buttons: [{ text: '编辑', click: item => (this.selectedField = item) }], title: '操作' }
  ];
  sql: string = `select * from menu;`;
  metaComFormGroup!: FormGroup;
  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.metaComFormGroup = this.fb.group({
      objectCode: [null, [Validators.required]],
      tableName: [null, [Validators.required]],
      pkKey: [null, [Validators.required]],
      groupName: [null, [Validators.required]]
    });
  }
  // metaCom?: MetaCom;
  // fields: Field[] = [];
  // power: number = C | U | D | Q | S;
  // selectedField?: Field;

  selectedPowers: number[] = [];
  // async onClose($event: any, i) {
  //   // this.metaCom.metaFields.splice(i, 1);
  //   // await this.refershMetaObject();
  // }

  ngOnInit() {}
  submit() {
    let metaCom = this.metaComFormGroup.value;
    metaCom.metaFields = this.fields;
    metaCom.querySql = this.sql;

    this.http.post(`/api/addMetaObject`, metaCom).toPromise();
  }
  // editField(field: Field) {
  //   this.selectedField = field;
  //   this.editFieldVisible = true;
  //   this.selectedPowers = [];
  //   if (checkPower(this.selectedField.power as any, C)) {
  //     this.selectedPowers.push(C);
  //   }
  //   if (checkPower(this.selectedField.power as any, S)) {
  //     this.selectedPowers.push(S);
  //   }
  //   if (checkPower(this.selectedField.power as any, U)) {
  //     this.selectedPowers.push(U);
  //   }
  // }

  // getRandomColor(field: Field) {
  //   switch (field.type) {
  //     case String:
  //       return '#2db7f5';
  //     case Number:
  //       return '#87d068';
  //     case Date:
  //       return '#f50';
  //   }
  // }
  async queryMeta() {
    let result: any = await this.http.post('/api/field', { sql: this.sql }).toPromise();

    this.fields = result.data.map((field: { field: string; type: SQLFieldType }) => this.sqlFieldToField(field));
    this.metaCom = {
      // objectCode: EntityEnum.User,
      data: { presetConditions: [] },
      metaFields: this.fields,
      view: { pageSize: 10 }
      // originClass: Object
    };
  }

  sqlFieldToField(field: { field: string; type: SQLFieldType }): Field {
    switch (field.type) {
      case 'VARCHAR':
        return { alias: field.field, fieldName: field.field, fieldType: field.type };
      case 'INT':
        return { alias: field.field, fieldName: field.field, fieldType: field.type };
      case 'DATETIME':
        return { alias: field.field, fieldName: field.field, fieldType: field.type };
      default:
        return null as any;
    }
  }

  async confirmEditField() {
    // this.selectedField.power = this.selectedPowers.concat([0, 0]).reduce((val1, val2) => val1 | val2);
    // await this.refershMetaObject();
  }

  sleep(time: number) {
    return new Promise(resolve =>
      setTimeout(() => {
        resolve(true);
      }, time)
    );
  }
  async refershMetaObject() {
    // let obj = this.metaCom;
    // this.metaCom = null;
    // await this.sleep(200);
    // this.metaCom = obj;
  }

  getIndex() {
    // return this.metaCom.metaFields.indexOf(this.selectedField);
  }

  minIndex() {
    // if (this.getIndex() - 1 >= 0) {
    //   let preField = this.metaCom.metaFields[this.getIndex() - 1];
    //   this.metaCom.metaFields.splice(this.getIndex() - 1, 2, this.selectedField, preField);
    //   this.refershMetaObject();
    // }
  }

  addIndex() {
    // if (this.getIndex() + 1 <= this.metaCom.metaFields.length) {
    //   let afterField = this.metaCom.metaFields[this.getIndex() + 1];
    //   this.metaCom.metaFields.splice(this.getIndex(), 2, afterField, this.selectedField);
    //   this.refershMetaObject();
    // }
  }

  getSource() {
    // return this.getClazz();
  }

  private getClazz() {
    //   return `@MetaEntity()
    //   export class Entity{
    //     ${this.getBody()}
    //   }
    // `;
  }
  // private getBody(): string {
  //   let fieldsStr = '';
  //   this.metaCom.metaFields.forEach(field => (fieldsStr += this.getField(field)));
  //   return fieldsStr;
  // }
  // private getField(field: Field) {
  //   let type = '';
  //   switch (field.type) {
  //     case Number:
  //       type = 'number';
  //       break;
  //     case String:
  //       type = 'string';
  //       break;
  //     case Date:
  //       type = 'Date';
  //       break;
  //   }
  // let powers: string[] = [];
  // // let c = checkPower(field.power as any, C);
  // // if (c) powers.push('C');
  // // let u = checkPower(field.power as any, U);
  // // if (u) powers.push('U');
  // // let s = checkPower(field.power as any, S);
  // // if (s) powers.push('S');

  // return `
  // @Prop("${field.alias || field.fieldName}",{power:${powers.length > 0 ? powers.join('|') : 0}})
  // ${field.fieldName}:${type};`;
  // }

  // viewSource() {
  //   let code = this.getSource();
  //   console.log(code);
  // }
}
