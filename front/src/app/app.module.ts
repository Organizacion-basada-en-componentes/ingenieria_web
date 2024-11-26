import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HomePacienteComponent } from './home-paciente/home-paciente.component'; // No standalone
import { HomeMedicoComponent } from './home-medico/home-medico.component'; // No standalone

import { FooterComponent } from './footer/footer.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

import { HeaderLoggedInComponent } from './header-logged-in/header-logged-in.component';
import { HeaderLoggedOutComponent } from './header-logged-out/header-logged-out.component';

@NgModule({
  declarations: [
    AppComponent, // No olvides incluir AppComponent en las declaraciones
    FooterComponent,
    LandingPageComponent,
    
    RegisterComponent,
    LoginComponent,
    
    HomePacienteComponent,
    HomeMedicoComponent,

    HeaderLoggedInComponent,
    HeaderLoggedOutComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
