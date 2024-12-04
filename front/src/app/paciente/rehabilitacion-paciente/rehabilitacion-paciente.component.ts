import { Component } from '@angular/core';
import { Router } from '@angular/router';  // Importa Router

@Component({
  selector: 'app-rehabilitacion-paciente',
  standalone: false,
  templateUrl: './rehabilitacion-paciente.component.html',
  styleUrls: ['./rehabilitacion-paciente.component.css']
})
export class RehabilitacionPacienteComponent {
  // Lista de planes de rehabilitación
  planes = [
    {
      nombre: 'Plan de Rehabilitación de Rodilla',
      fecha: new Date(),
      ejercicios: [
        { nombre: 'Sentadillas', series: 3, repeticiones: 12, descripcion: 'Bajar y subir manteniendo la postura correcta.' },
        { nombre: 'Elevaciones de pierna', series: 3, repeticiones: 15, descripcion: 'Levantar pierna para fortalecer la musculatura.' },
        { nombre: 'Flexiones de rodilla', series: 3, repeticiones: 10, descripcion: 'Flexionar y extender la rodilla para mejorar su movilidad.' }
      ]
    },
    {
      nombre: 'Plan de Rehabilitación de Espalda',
      fecha: new Date(),
      ejercicios: [
        { nombre: 'Estiramientos de espalda', series: 2, repeticiones: 10, descripcion: 'Estirar la espalda suavemente.' },
        { nombre: 'Flexiones de torso', series: 3, repeticiones: 12, descripcion: 'Flexionar el torso hacia adelante para flexibilizar la columna.' },
        { nombre: 'Rotaciones de tronco', series: 3, repeticiones: 10, descripcion: 'Rotar el torso para mejorar la movilidad.' }
      ]
    }
  ];

  constructor(private router: Router) {}  // Inyecta el Router

  // Método para navegar con el plan seleccionado
  seleccionarPlan(plan: any) {
    // Navegar con un parámetro de ruta (en este caso, pasamos un identificador o un objeto serializado)
    this.router.navigate(['plan-rehabilitacion-paciente', { plan: JSON.stringify(plan) }]);
  }
}
