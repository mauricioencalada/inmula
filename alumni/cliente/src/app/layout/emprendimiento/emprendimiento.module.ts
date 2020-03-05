import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmprendimientoRoutingModule } from './emprendimiento-routing.module';
import { EmprendimientoComponent } from './emprendimiento.component';


@NgModule({
  declarations: [EmprendimientoComponent],
  imports: [
    CommonModule,
    EmprendimientoRoutingModule
  ]
})
export class EmprendimientoModule { }
