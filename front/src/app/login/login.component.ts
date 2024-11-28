import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: false, // Indica que es un componente standalone
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
onSubmit() {
throw new Error('Method not implemented.');
}
  constructor(private authService: AuthService, private router: Router) {}

  loginAsPaciente() {
    this.authService.login('paciente');
    this.router.navigate(['/home-paciente']);
  }

  loginAsMedico() {
    this.authService.login('medico');
    this.router.navigate(['/home-medico']);
  }
  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}
