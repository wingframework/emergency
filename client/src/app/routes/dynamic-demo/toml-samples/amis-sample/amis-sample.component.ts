import { Component, ElementRef, Input, ViewChild } from '@angular/core';

@Component({ selector: 'amis-sample', templateUrl: './amis-sample.component.html', styleUrls: ['./amis-sample.component.css'] })
export class AmisSampleComponent {
  @ViewChild('sample') sampleEl!: ElementRef<HTMLDivElement>;
  ngAfterViewInit(): void {
    let amisScoped = amis.embed(this.sampleEl.nativeElement, this.json);
    debugger;
  }
  @Input() title!: string;
  @Input() json!: string | object;
}
