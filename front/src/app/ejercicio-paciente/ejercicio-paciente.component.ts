import { Component } from '@angular/core';

@Component({
  selector: 'app-ejercicio-paciente',
  standalone: false,
  templateUrl: './ejercicio-paciente.component.html',
  styleUrls: ['./ejercicio-paciente.component.css']
})
export class EjercicioPacienteComponent {
  ejercicio = {
    nombre: 'Sentadillas',
    series: 3,
    repeticiones: 15,
    descripcion: 'Realiza sentadillas lentas y profundas, asegurándote de mantener una postura adecuada.'
  };

  completado: boolean = false;
  comentario: string = '';

  guardarFeedback() {
    const feedback = {
      completado: this.completado,
      comentario: this.comentario
    };
    console.log('Feedback guardado:', feedback);
    alert('Tu feedback ha sido guardado exitosamente.');
  }
}
