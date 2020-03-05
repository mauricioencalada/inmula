import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployabilityRoutingModule } from './employability-routing.module';
import {EmployabilityComponent} from './employability.component';

import {TableModule} from 'primeng/table';



@NgModule({
  declarations: [EmployabilityComponent],
  imports: [
    CommonModule,
    EmployabilityRoutingModule,
    TableModule,
    FormsModule
  ]
})
export class EmployabilityModule { }
