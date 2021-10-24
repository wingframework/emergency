import { Component, ElementRef, Input, ViewChild } from '@angular/core';

@Component({ selector: 'amis-sample', templateUrl: './amis-sample.component.html' })
export class AmisSampleComponent {
  @ViewChild('sample') sampleEl!: ElementRef<HTMLDivElement>;
  ngAfterViewInit(): void {
    debugger;
    let amisScoped = amis.embed(this.sampleEl.nativeElement, this.json);
  }
  @Input() title!: string;
  @Input() json!: string | object;
}
