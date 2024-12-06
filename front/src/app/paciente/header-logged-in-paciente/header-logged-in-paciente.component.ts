import { Component, Inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-logged-in-paciente',
  standalone: false,
  
  templateUrl: './header-logged-in-paciente.component.html',
  styleUrl: './header-logged-in-paciente.component.css'
})
export class HeaderLoggedInPacienteComponent {
  
  
  constructor(private router: Router,@Inject(AuthService) private authService: AuthService) {}
  
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
  goToRehabilitacion(): void {
    this.router.navigate(['rehabilitacion-paciente']);
  }
  goToHome(): void {
    this.router.navigate(['/home-paciente']);
  }
}
