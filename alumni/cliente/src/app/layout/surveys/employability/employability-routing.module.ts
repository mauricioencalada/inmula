import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployabilityComponent} from './employability.component';


const routes: Routes = [{
  path: '',
  component: EmployabilityComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployabilityRoutingModule { }
