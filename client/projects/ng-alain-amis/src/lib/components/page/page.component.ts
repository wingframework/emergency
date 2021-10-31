import { Component, Input } from '@angular/core';

@Component({ selector: 'amis-page', templateUrl: './page.component.html' })
export class PageComponent {
  @Input() title!: string;
  @Input() body!: any;
  constructor() {}
  ngOnInit(): void {
    this.title;
    debugger;
  }
}
