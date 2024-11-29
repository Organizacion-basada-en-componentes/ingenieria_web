import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';  
import { LoginComponent } from './login/login.component';
import { HomePacienteComponent } from './home-paciente/home-paciente.component';
import { HomeMedicoComponent } from './home-medico/home-medico.component';
import { AuthGuard } from './auth.guard';
import { RegisterComponent } from './register/register.component';
import { PlanRehabilitacionPacienteComponent } from './plan-rehabilitacion-paciente/plan-rehabilitacion-paciente.component';
import { RehabilitacionPacienteComponent } from './rehabilitacion-paciente/rehabilitacion-paciente.component';
import { AccesoDenegadoComponent } from './acceso-denegado/acceso-denegado.component'; // Puedes crear esta página
import { ProgresoComponent } from './progreso/progreso.component';
import { ComunicacionComponent } from './comunicacion/comunicacion.component';
import { PedirCitaComponent } from './comunicacion/pedir-cita/pedir-cita.component';
import { MensajeComponent } from './comunicacion/mensaje/mensaje.component';
import { VideollamadaComponent } from './comunicacion/videollamada/videollamada.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },  // Landing page por defecto
  { path: 'login', component: LoginComponent },  // Ruta para login
  { path: 'register', component: RegisterComponent },  // Ruta para registro
  
  // Rutas para pacientes, protegidas por AuthGuard
  { path: 'home-paciente', component: HomePacienteComponent, canActivate: [AuthGuard], data: { role: 'paciente' } },
  { path: 'plan-rehabilitacion-paciente', component: PlanRehabilitacionPacienteComponent, canActivate: [AuthGuard], data: { role: 'paciente' } },
  { path: 'rehabilitacion-paciente', component: RehabilitacionPacienteComponent, canActivate: [AuthGuard], data: { role: 'paciente' } },

  // Rutas para médicos, protegidas por AuthGuard
  { path: 'home-medico', component: HomeMedicoComponent, canActivate: [AuthGuard], data: { role: 'medico' } },
  { path: 'progreso', component: ProgresoComponent },
  { path: 'comunicacion', component: ComunicacionComponent },
  { path: 'comunicacion/pedir-cita', component: PedirCitaComponent },
  { path: 'comunicacion/mensaje', component: MensajeComponent },
  { path: 'comunicacion/videollamada', component: VideollamadaComponent },
  // Ruta de acceso denegado
  { path: 'acceso-denegado', component: AccesoDenegadoComponent },

  // Ruta comodín para redirigir a la landing page
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
