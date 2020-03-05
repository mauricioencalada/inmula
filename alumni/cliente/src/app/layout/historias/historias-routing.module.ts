import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HistoriasComponent } from './historias.component';


const routes: Routes = [{
  path: "",
  component: HistoriasComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HistoriasRoutingModule { }
