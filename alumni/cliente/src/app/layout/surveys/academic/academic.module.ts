import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AcademicRoutingModule } from './academic-routing.module';
import {AcademicComponent} from './academic.component';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [AcademicComponent],
  imports: [
    CommonModule,
    AcademicRoutingModule,
    TableModule,
    FormsModule
  ]
})
export class AcademicModule { }
