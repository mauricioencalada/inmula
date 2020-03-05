import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GeneratorSurveyComponent} from './generator-survey.component';


const routes: Routes = [{
  path: '',
  component: GeneratorSurveyComponent
}];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GeneratorSurveyRoutingModule {
}
