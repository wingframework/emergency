import { TestBed } from '@angular/core/testing';

import { NgAlainAmisService } from './ng-alain-amis.service';

describe('NgAlainAmisService', () => {
  let service: NgAlainAmisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NgAlainAmisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
