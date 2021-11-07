import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NgAlainAmisComponent } from './ng-alain-amis.component';

describe('NgAlainAmisComponent', () => {
  let component: NgAlainAmisComponent;
  let fixture: ComponentFixture<NgAlainAmisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NgAlainAmisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NgAlainAmisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
