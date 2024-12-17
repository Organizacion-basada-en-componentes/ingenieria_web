import { Component, Inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-logged-in-medico',
  standalone: false,
  templateUrl: './header-logged-in-medico.component.html',
  styleUrl: './header-logged-in-medico.component.css'
})
export class HeaderLoggedInMedicoComponent {
  menuVisible: boolean = false;

  constructor(private router: Router, @Inject(AuthService) private authService: AuthService) {}

  toggleMenu(): void {
    this.menuVisible = !this.menuVisible;
    const headerActions = document.querySelector('.header-actions') as HTMLElement;
    if (this.menuVisible) {
      headerActions.classList.add('active');
    } else {
      headerActions.classList.remove('active');
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  goToHome() {
    this.router.navigate(['/home-medico']);
  }

  goToComunicacion() {
    this.router.navigate(['/comunicacion']);
  }

  goToRehabilitacion() {
    this.router.navigate(['/rehabilitacion-medico']);
  }
}
