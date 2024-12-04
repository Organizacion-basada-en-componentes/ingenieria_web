import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-ejercicio-paciente',
  standalone: false,
  templateUrl: './ejercicio-paciente.component.html',
  styleUrls: ['./ejercicio-paciente.component.css']
})
export class EjercicioPacienteComponent {
  @Input() ejercicio: { nombre: string; series: number; repeticiones: number; descripcion: string } = {
    nombre: 'Nombre del ejercicio',
    series: 0,
    repeticiones: 0,
    descripcion: 'Descipcion del ejercicio.'
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
