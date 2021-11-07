import { Component, Input, OnInit } from '@angular/core';


@Component({ selector: 'page', templateUrl: './page.component.html' })
export class PageComponent implements OnInit {
  @Input() title!: string;
  @Input() body!: any;
  constructor() {}
  ngOnInit(): void {}
}
