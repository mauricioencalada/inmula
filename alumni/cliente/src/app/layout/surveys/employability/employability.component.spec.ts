import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployabilityComponent } from './employability.component';

describe('EmployabilityComponent', () => {
  let component: EmployabilityComponent;
  let fixture: ComponentFixture<EmployabilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployabilityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
