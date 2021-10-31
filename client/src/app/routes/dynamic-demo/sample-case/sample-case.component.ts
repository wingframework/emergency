import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';

@Component({
  selector: 'sample-case',
  templateUrl: './sample-case.component.html'
})
export class SampleCaseComponent implements AfterViewInit {
  @ViewChild('sample') sampleEl!: ElementRef<HTMLDivElement>;
  ngAfterViewInit(): void {
    let amisScoped = amis.embed(this.sampleEl.nativeElement, this.json);
  }
  @Input() title!: string;
  @Input() json!: string | object;
}
