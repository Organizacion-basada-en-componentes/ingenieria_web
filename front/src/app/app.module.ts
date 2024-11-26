import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HeaderLoggedInComponent } from './header-logged-in/header-logged-in.component';
import { HeaderLoggedOutComponent } from './header-logged-out/header-logged-out.component';

@NgModule({
  declarations: [
    AppComponent, // No olvides incluir AppComponent en las declaraciones
    FooterComponent,
    LandingPageComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    HeaderLoggedInComponent,
    HeaderLoggedOutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
