import { Routes } from '@angular/router';
// Revisa si tu archivo se llama login.ts o login.component.ts
import { LoginComponent } from './components/login/login';
// En tu captura vi que el archivo se llama "producto-lista.ts" no "producto-lista.component.ts"
import { ProductoListaComponent } from './components/producto-lista/producto-lista';
// Revisa si es auth-guard o auth.guard
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'productos',
    component: ProductoListaComponent,
    canActivate: [authGuard]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }
];
