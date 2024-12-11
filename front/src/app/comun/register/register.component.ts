import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service'; // Asegúrate de que el servicio está importado

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  email: string = '';
  nombre: string = '';
  dni: string = '';
  especialidad: string = '';
  fechaNacimiento: string = '';
  role: string = 'PACIENTE'; // Por defecto es paciente
  errorMessage: string | null = null;
  debugInfo: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  onRoleChange(event: any) {
    this.role = event.target.value;
    console.log('Role seleccionado:', this.role);
  }

  onSubmit() {
    // Validación de campos
    if (!this.username || !this.password || !this.email || !this.nombre || (this.role === 'MEDICO' && !this.dni)) {
      this.errorMessage = 'Por favor, completa todos los campos obligatorios.';
      this.debugInfo = `Campos faltantes:
        Username: ${!this.username ? 'Faltante' : 'Completado'},
        Password: ${!this.password ? 'Faltante' : 'Completado'},
        Email: ${!this.email ? 'Faltante' : 'Completado'},
        Nombre: ${!this.nombre ? 'Faltante' : 'Completado'},
        DNI: ${this.role === 'MEDICO' && !this.dni ? 'Faltante' : 'Completado'}`;
      console.warn('Campos faltantes:', {
        username: this.username,
        password: this.password,
        email: this.email,
        nombre: this.nombre,
        dni: this.dni,
      });
      return;
    }

    const userData = {
      username: this.username,
      password: this.password,
      email: this.email,
      role: this.role,
      nombre: this.nombre,
      dni: this.dni ,
      especialidad: this.role === 'MEDICO' ? this.especialidad : null,
      fechaNacimiento: this.role === 'PACIENTE' ? this.fechaNacimiento : null,
    };

    console.log('Datos a enviar:', userData);

    this.authService.register(userData).subscribe({
      next: () => {
        console.log('Registro exitoso');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.errorMessage = 'Error en el registro. Intente nuevamente.';
        this.debugInfo = 'Error en el servidor. Detalles: ' + err.message;
        console.error('Error en el registro:', err);
      },
    });
  }
}
