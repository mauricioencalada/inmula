import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GeneratorSurveyRoutingModule } from './generator-survey-routing.module';
import {GeneratorSurveyComponent} from './generator-survey.component';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [GeneratorSurveyComponent],
  imports: [
    CommonModule,
    TableModule,
    FormsModule,
    GeneratorSurveyRoutingModule
  ]
})
export class GeneratorSurveyModule { }
