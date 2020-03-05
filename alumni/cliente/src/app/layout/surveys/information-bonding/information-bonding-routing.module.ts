import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {InformationBondingComponent} from './information-bonding.component';



const routes: Routes = [{
  path: '',
  component: InformationBondingComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InformationBondingRoutingModule { }
