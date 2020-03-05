import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutRoutingModule } from './layout-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LayoutComponent} from './layout.component';





@NgModule({
  declarations: [LayoutComponent,],
  imports: [
    CommonModule,
    LayoutRoutingModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class LayoutModule { }
