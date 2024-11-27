import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';  // Importa la landing-page
import { LoginComponent } from './login/login.component';
import { HomePacienteComponent } from './home-paciente/home-paciente.component';
import { HomeMedicoComponent } from './home-medico/home-medico.component';
import { AuthGuard } from './auth.guard';
import { RegisterComponent } from './register/register.component';
const routes: Routes = [
  { path: '', component: LandingPageComponent },  // Landing page por defecto
  { path: 'login', component: LoginComponent },  // Ruta para login
  { path: 'register', component: RegisterComponent },  // Ruta para registro
  { path: 'home-paciente', component: HomePacienteComponent, canActivate: [AuthGuard] },
  { path: 'home-medico', component: HomeMedicoComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }  // Redirigir a la landing-page en rutas no definidas
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

