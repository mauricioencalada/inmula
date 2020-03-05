import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneratorSurveyComponent } from './generator-survey.component';

describe('GeneratorSurveyComponent', () => {
  let component: GeneratorSurveyComponent;
  let fixture: ComponentFixture<GeneratorSurveyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeneratorSurveyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneratorSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
