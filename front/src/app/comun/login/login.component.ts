import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        // Redirige basado en el rol del usuario si se gestiona en el backend
        if (this.authService.getUserType() === 'paciente') {
          this.router.navigate(['/home-paciente']);
        }
        if (this.authService.getUserType() === 'medico') {
          this.router.navigate(['/home-medico']);
        }
      },
      error: (err) => {
        this.errorMessage = 'Credenciales inválidas. Intente de nuevo.';
        console.error(err);
      },
    });
  }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}
