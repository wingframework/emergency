import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CopyService } from 'src/app/shared/utils/copy.service';
import * as toml from 'toml';
@Component({
  selector: 'dynamic-route-samples',
  templateUrl: './dynamic-route-samples.component.html',
  styleUrls: ['./dynamic-route-samples.component.css']
})
export class DynamicRouteSamplesComponent implements AfterViewInit {
  file!: string;
  body!: any;
  tomlText!: string;
  showAlainCode = false;
  settingFormat: 'toml' | 'json' = 'toml';
  @ViewChild('sample') sampleEl!: ElementRef<HTMLDivElement>;
  constructor(
    private route: ActivatedRoute,
    private httpClient: HttpClient,
    private copyService: CopyService,
    private msgService: NzMessageService
  ) {
    this.route.params.subscribe(p => {
      this.file = p.file;
      this.reload();
    });
  }
  ngAfterViewInit() {}

  async reload() {
    this.tomlText = await this.httpClient.get(`/assets/sample/` + this.file, { responseType: 'text' }).toPromise();
    debugger;
    this.body = toml.parse(this.tomlText);
    debugger;
    let amisScoped = amis.embed(this.sampleEl.nativeElement, this.body);
    this.refreshHighlight();
  }
  refreshHighlight() {
    setTimeout(() => {
      hljs.highlightAll();
    }, 100);
  }
  copyText(value: string) {
    this.copyService.copyText(value);
    this.msgService.success('复制成功');
  }
  stringify(data: any) {
    return JSON.stringify(data);
  }
}
