import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EncuestaRoutingModule } from './encuesta-routing.module';
import {EncuestaComponent} from './encuesta.component';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [EncuestaComponent],
  imports: [
    CommonModule,
    EncuestaRoutingModule,
    TableModule,
    FormsModule
  ]
})
export class EncuestaModule { }
