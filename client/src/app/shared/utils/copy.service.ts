import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class CopyService {
  copyText(value: string) {
    var oInput = document.createElement('textarea');
    document.body.appendChild(oInput);
    oInput.value = value;
    oInput.select(); // 选择对象
    document.execCommand('Copy'); // 执行浏览器复制命令
    oInput.remove();
  }
}
