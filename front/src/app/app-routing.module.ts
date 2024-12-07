import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './comun/landing-page/landing-page.component';  
import { LoginComponent } from './comun/login/login.component';
import { HomePacienteComponent } from './paciente/home-paciente/home-paciente.component';
import { HomeMedicoComponent } from './medico/home-medico/home-medico.component';
import { AuthGuard } from './services/auth.guard';
import { RegisterComponent } from './comun/register/register.component';
import { PlanRehabilitacionPacienteComponent } from './paciente/plan-rehabilitacion-paciente/plan-rehabilitacion-paciente.component';
import { RehabilitacionPacienteComponent } from './paciente/rehabilitacion-paciente/rehabilitacion-paciente.component';
import { AccesoDenegadoComponent } from './comun/acceso-denegado/acceso-denegado.component'; // Puedes crear esta página
import { ProgresoComponent } from './progreso/progreso.component';
import { ComunicacionComponent } from './comunicacion/comunicacion.component';
import { PedirCitaComponent } from './comunicacion/pedir-cita/pedir-cita.component';
import { MensajeComponent } from './comunicacion/mensaje/mensaje.component';
import { VideollamadaComponent } from './comunicacion/videollamada/videollamada.component';
import { SelectedPatientComponent } from './medico/selected-patient/selected-patient.component';
import { SelectedAlertaComponent } from './medico/selected-alerta/selected-alerta.component';
import { RehabilitacionMedicoComponent } from './medico/rehabilitacion-medico/rehabilitacion-medico.component';
import { PlanRehabilitacionMedicoComponent } from './medico/plan-rehabilitacion-medico/plan-rehabilitacion-medico.component';
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
  { path: 'paciente-seleccionado', component: SelectedPatientComponent },
  {path : 'alerta-seleccionada', component: SelectedAlertaComponent},
  {path : 'rehabilitacion-medico', component: RehabilitacionMedicoComponent},
  {path : 'plan-rehabilitacion-medico', component: PlanRehabilitacionMedicoComponent},
  // Ruta comodín para redirigir a la landing page
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
