import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component'; // Standalone Component
import { HomePacienteComponent } from './home-paciente/home-paciente.component'; // No standalone
import { HomeMedicoComponent } from './home-medico/home-medico.component'; // No standalone

@NgModule({
  declarations: [
    AppComponent,
    HomePacienteComponent,
    HomeMedicoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginComponent, // Importamos el standalone
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
