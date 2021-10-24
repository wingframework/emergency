import { Component, Input, OnInit } from '@angular/core';

import { ComponentRegisterFactoryService } from '../core/omponent-register-factory.service';
import { Com } from '../typings';

@Component({ selector: 'page', templateUrl: './page.component.html' })
export class PageComponent implements OnInit {
  @Input() body!: Com & any;
  constructor(private componentRegisterFactoryService: ComponentRegisterFactoryService) {}
  ngOnInit(): void {}
}
