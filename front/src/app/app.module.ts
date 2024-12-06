import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HomePacienteComponent } from './paciente/home-paciente/home-paciente.component'; // No standalone
import { HomeMedicoComponent } from './medico/home-medico/home-medico.component'; // No standalone

import { FooterComponent } from './comun/footer/footer.component';
import { LandingPageComponent } from './comun/landing-page/landing-page.component';
import { RegisterComponent } from './comun/register/register.component';
import { LoginComponent } from './comun/login/login.component';

import { HeaderLoggedOutComponent } from './comun/header-logged-out/header-logged-out.component';
import { HeaderLoggedInMedicoComponent } from './medico/header-logged-in-medico/header-logged-in-medico.component';
import { HeaderLoggedInMedicoNoPatientComponent } from './medico/header-logged-in-medico-no-patient/header-logged-in-medico-no-patient.component';

import { HeaderLoggedInPacienteComponent } from './paciente/header-logged-in-paciente/header-logged-in-paciente.component';

import { FormsModule } from '@angular/forms';
import { EjercicioPacienteComponent } from './paciente/ejercicio-paciente/ejercicio-paciente.component';
import { PlanRehabilitacionPacienteComponent } from './paciente/plan-rehabilitacion-paciente/plan-rehabilitacion-paciente.component';
import { RehabilitacionPacienteComponent } from './paciente/rehabilitacion-paciente/rehabilitacion-paciente.component';
import { AccesoDenegadoComponent } from './comun/acceso-denegado/acceso-denegado.component';
import { ProgresoComponent } from './progreso/progreso.component';
import { ComunicacionComponent } from './comunicacion/comunicacion.component';
import { MensajeComponent } from './comunicacion/mensaje/mensaje.component';
import { PedirCitaComponent } from './comunicacion/pedir-cita/pedir-cita.component';
import { VideollamadaComponent } from './comunicacion/videollamada/videollamada.component';
import { SelectedPatientComponent } from './medico/selected-patient/selected-patient.component';
import { SelectedAlertaComponent } from './medico/selected-alerta/selected-alerta.component';
import { LoadingComponent } from './comun/loading/loading.component';

@NgModule({
  declarations: [
    AppComponent, // No olvides incluir AppComponent en las declaraciones
    FooterComponent,
    LandingPageComponent,
    
    RegisterComponent,
    LoginComponent,
    
    HomePacienteComponent,
    HomeMedicoComponent,

    HeaderLoggedOutComponent,
    HeaderLoggedInMedicoComponent,
    HeaderLoggedInPacienteComponent,
    EjercicioPacienteComponent,
    PlanRehabilitacionPacienteComponent,
    RehabilitacionPacienteComponent,
    AccesoDenegadoComponent,
    ProgresoComponent,
    ComunicacionComponent,
    MensajeComponent,
    PedirCitaComponent,
    VideollamadaComponent,
    HeaderLoggedInMedicoNoPatientComponent,
    SelectedPatientComponent,
    SelectedAlertaComponent,
    LoadingComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    
],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
