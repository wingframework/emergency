import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Field } from '../../../types/field';

@Component({ selector: 'edit-field-modal', templateUrl: './edit-field-modal.component.html' })
export class EditFieldModalComponent {
  editFieldVisible = true;
  @Output() OnSave = new EventEmitter();
  @Input() field!: Field;
  selectedPowers = [];
  listOfOption: Array<{ label: string; value: any }> = [
    { label: '创建', value: 'C' },
    { label: '更新', value: 'U' },
    { label: '删除', value: 'Delete' },
    { label: '查询', value: 'Query' },
    { label: '显示', value: 'Show' }
  ];
  confirmEditField() {
    this.OnSave.emit();
  }
}
