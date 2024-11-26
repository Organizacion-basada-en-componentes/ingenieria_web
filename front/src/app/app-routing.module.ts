import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';
const routes: Routes = [
  // Define que la ruta raíz ('') apunta al LandingPageComponent
  { path: '', component: LandingPageComponent },
  
  
  // Ruta para manejar cualquier otra ruta desconocida
  { path: '**', redirectTo: '' } // redirige a la landing page en caso de ruta no encontrada
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
