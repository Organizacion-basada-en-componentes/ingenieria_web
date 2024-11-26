import { Component } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    // Inicializa el estado de la sesión al cargar la aplicación
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  toggleLoginState() {
    if (this.isLoggedIn) {
      this.authService.logout();
    } else {
      this.authService.login();
    }
    this.isLoggedIn = this.authService.isLoggedIn();
  }
}
