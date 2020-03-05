import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationBondingComponent } from './information-bonding.component';

describe('InformationBondingComponent', () => {
  let component: InformationBondingComponent;
  let fixture: ComponentFixture<InformationBondingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InformationBondingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InformationBondingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
