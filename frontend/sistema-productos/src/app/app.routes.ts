import { Routes } from '@angular/router';
import { ProductoListaComponent } from './components/producto-lista/producto-lista'; // <-- QUITA EL .ts AQUÍ

export const routes: Routes = [
  { path: '', component: ProductoListaComponent },
  { path: 'productos', component: ProductoListaComponent }
];
