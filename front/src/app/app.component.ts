import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';  // Importa AuthService

@Component({
  selector: 'app-root',
  standalone: false, // Indica que es un componente standalone
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isAuthenticated: boolean = false;  // Definir la propiedad

  constructor(private authService: AuthService) {}

  ngOnInit() {
    // Obtener el estado de autenticación desde el AuthService
    this.isAuthenticated = this.authService.isLoggedIn();
  }
}
