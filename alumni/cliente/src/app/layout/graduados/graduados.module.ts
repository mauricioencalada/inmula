import { GraduadosComponent } from './graduados.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GraduadosRoutingModule } from './graduados-routing.module';


@NgModule({
  declarations: [GraduadosComponent],
  imports: [
    CommonModule,
    GraduadosRoutingModule
  ]
})
export class GraduadosModule { }
