import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DifProyComponent } from './dif-proy.component';

describe('DifProyComponent', () => {
  let component: DifProyComponent;
  let fixture: ComponentFixture<DifProyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DifProyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DifProyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
