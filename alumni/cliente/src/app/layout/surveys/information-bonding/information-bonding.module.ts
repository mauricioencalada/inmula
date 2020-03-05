import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


import { InformationBondingRoutingModule } from './information-bonding-routing.module';
import {InformationBondingComponent} from './information-bonding.component';


@NgModule({
  declarations: [InformationBondingComponent],
  imports: [
    CommonModule,
    InformationBondingRoutingModule,
    FormsModule
  ]
})
export class InformationBondingModule { }
