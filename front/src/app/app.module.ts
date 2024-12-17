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
import { ComunicacionComponent } from './comunicacion/comunicacion.component';
import { MensajeComponent } from './comunicacion/mensaje/mensaje.component';
import { PedirCitaComponent } from './comunicacion/pedir-cita/pedir-cita.component';
import { SelectedPatientComponent } from './medico/selected-patient/selected-patient.component';
import { LoadingComponent } from './comun/loading/loading.component';
import { RehabilitacionMedicoComponent } from './medico/rehabilitacion-medico/rehabilitacion-medico.component';
import { PlanRehabilitacionMedicoComponent } from './medico/plan-rehabilitacion-medico/plan-rehabilitacion-medico.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './services/auth.interceptor';
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
    ComunicacionComponent,
    MensajeComponent,
    PedirCitaComponent,
    HeaderLoggedInMedicoNoPatientComponent,
    SelectedPatientComponent,
    LoadingComponent,
    RehabilitacionMedicoComponent,
    PlanRehabilitacionMedicoComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
    
],
  providers: [    {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true, // Permite usar múltiples interceptores
  }],
  bootstrap: [AppComponent],
})
export class AppModule {}
