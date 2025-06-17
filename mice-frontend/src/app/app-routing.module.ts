import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { authGuard } from './guards/auth.guard';


const routes: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('./login/login.component').then((m) => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./register/register.component').then((m) => m.RegisterComponent),
    canActivate: [authGuard],
  },
  {
    path: 'edit-user/:id',
    loadComponent: () =>
      import('./edit-user/edit-user.component').then((m) => m.EditUserComponent),
    canActivate: [authGuard],
  },
  {
    path: 'exposition',
    loadComponent: () =>
      import('./exposition/exposition.component').then((m) => m.ExpositionComponent),
    canActivate: [authGuard],
  },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./dashboard/dashboard.component').then((m) => m.DashboardComponent),
    canActivate: [authGuard],
  },
  {
    path: 'evenement',
    loadComponent: () =>
      import('./evenement/evenement.component').then((m) => m.EvenementComponent),
    canActivate: [authGuard],
  },
  {
    path: 'user',
    loadComponent: () =>
      import('./user/user.component').then((m) => m.UserComponent),
    canActivate: [authGuard],
  },
  {
    path: 'add-salle',
    loadComponent: () =>
      import('./add-salle/add-salle.component').then((m) => m.AddSalleComponent),
    canActivate: [authGuard],
  },
  {
    path: 'equipement',
    loadComponent: () =>
      import('./equipement/equipement.component').then((m) => m.EquipementComponent),
    canActivate: [authGuard],
  },
  {
    path: 'stand',
    loadComponent: () =>
      import('./stand/stand.component').then((m) => m.StandComponent),
    canActivate: [authGuard],
  },
  {
    path: 'unauthorized',
    loadComponent: () =>
      import('./unauthorized/unauthorized.component').then((m) => m.UnauthorizedComponent),
  },
  
 
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
