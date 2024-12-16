import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-logged-out',
  standalone: false,
  templateUrl: './header-logged-out.component.html',
  styleUrls: ['./header-logged-out.component.css']
})
export class HeaderLoggedOutComponent {
  isMenuOpen = false; // Estado para el menú desplegable

  constructor(private router: Router) {}

  goToLogin(): void {
    this.router.navigate(['/login']);
    this.isMenuOpen = false; // Cerrar el menú después de navegar
  }
  
  goToRegister(): void {
    this.router.navigate(['/register']);
    this.isMenuOpen = false; // Cerrar el menú después de navegar
  }

  // Función para alternar el menú desplegable
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
