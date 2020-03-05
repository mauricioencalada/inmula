import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';


const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'surveys', pathMatch: 'prefix' },
      {
        path: 'encuesta',
        loadChildren: () =>
          import('./encuesta/encuesta.module')
            .then(m => m.EncuestaModule)
      },
      {
        path: 'graduados',
        loadChildren: () =>
          import('./graduados/graduados.module')
            .then(m => m.GraduadosModule)
      },
      {
        path: 'servicios',
        loadChildren: () =>
          import('./servicios/servicios.module')
            .then(m => m.ServiciosModule)
      },
      {
        path: 'emprendimiento',
        loadChildren: () =>
          import('./emprendimiento/emprendimiento.module')
            .then(m => m.EmprendimientoModule)
      },
      {
        path: 'about',
        loadChildren: () =>
          import('./about/about.module')
            .then(m => m.AboutModule)
      },
      {
        path: 'historias',
        loadChildren: () =>
          import('./historias/historias.module')
            .then(m => m.HistoriasModule)
      },
      {
        path: 'surveys',
        loadChildren: () =>
          import('./surveys/personal/personal.module')
            .then(m => m.PersonalModule)
      },
      {
        path: 'admin',
        loadChildren: () =>
          import('./generator-survey/generator-survey.module')
            .then(m => m.GeneratorSurveyModule)
      },
      {
        path: 'academic',
        loadChildren: () =>
          import('./surveys/academic/academic.module')
            .then(m => m.AcademicModule)
      },
      {
        path: 'employ',
        loadChildren: () =>
          import('./surveys/employability/employability.module')
            .then(m => m.EmployabilityModule)
      },
      {
        path: 'infobo',
        loadChildren: () =>
          import('./surveys/information-bonding/information-bonding.module')
            .then(m => m.InformationBondingModule)
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule {
}
