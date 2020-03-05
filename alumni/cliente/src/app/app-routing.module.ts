import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthGuard} from './services/auth.guard';


const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'prefix'},
  {path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule)},
  {path: 'web', loadChildren: () => import('./layout/layout.module').then(m => m.LayoutModule), canActivate: [AuthGuard]},
  {path: 'login', loadChildren: () => import('./login/login.module').then(m => m.LoginModule)},
  {path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule)},
  {path: 'access-denied', loadChildren: () => import('./access-denied/access-denied.module').then(m => m.AccessDeniedModule)},
  {path: 'not-found', loadChildren: () => import('./not-found/not-found.module').then(m => m.NotFoundModule)},
  {path: '**', redirectTo: 'not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
