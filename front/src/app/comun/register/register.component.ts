import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  onSubmit() {
    console.log('Formulario enviado');
    // Aquí puedes añadir la lógica para enviar datos al servidor.
  }
}
