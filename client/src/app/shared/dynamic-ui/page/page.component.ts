import { Component, Input, OnInit } from '@angular/core';

import { ComponentRegisterFactoryService } from '../core/omponent-register-factory.service';
import { Com } from '../typings';

@Component({ selector: 'page', templateUrl: './page.component.html' })
export class PageComponent implements OnInit {
  @Input() title!: string;
  @Input() body!: any;
  constructor() {}
  ngOnInit(): void {}
}
