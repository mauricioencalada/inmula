import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HistoriasRoutingModule } from './historias-routing.module';
import { HistoriasComponent } from './historias.component';


@NgModule({
  declarations: [HistoriasComponent],
  imports: [
    CommonModule,
    HistoriasRoutingModule
  ]
})
export class HistoriasModule { }
