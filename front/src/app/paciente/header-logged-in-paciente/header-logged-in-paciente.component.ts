import { Component, Inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-logged-in-paciente',
  standalone: false,
  templateUrl: './header-logged-in-paciente.component.html',
  styleUrls: ['./header-logged-in-paciente.component.css']
})
export class HeaderLoggedInPacienteComponent {
  // Propiedad para controlar la visibilidad del menú
  menuVisible: boolean = false;

  constructor(private router: Router, @Inject(AuthService) private authService: AuthService) {}

  // Función para cerrar sesión
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  // Función para redirigir a la página de rehabilitación
  goToRehabilitacion(): void {
    this.router.navigate(['rehabilitacion-paciente']);
  }
  goTocomunication(): void {
    this.router.navigate(['comunicacion']);
  }

  // Función para redirigir a la página de inicio
  goToHome(): void {
    this.router.navigate(['/home-paciente']);
  }


  // Método que navega a la ruta "/comunicacion"
  goToCommunication() {
    this.router.navigate(['/comunicacion']); // Navega a la ruta deseada
  }
  
  // Función para alternar el estado del menú
  toggleMenu(): void {
    this.menuVisible = !this.menuVisible;
    const headerActions = document.querySelector('.header-actions') as HTMLElement;
    if (this.menuVisible) {
      headerActions.classList.add('active');
    } else {
      headerActions.classList.remove('active');
    }
  }
}
