import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rehabilitacion-medico',
  standalone: false,
  templateUrl: './rehabilitacion-medico.component.html',
  styleUrls: ['./rehabilitacion-medico.component.css']
})
export class RehabilitacionMedicoComponent {
  planes = [
    {
      nombre: 'Plan Inicial de Rehabilitación',
      fecha: new Date(),
      ejercicios: [
        { nombre: 'Ejercicio 1', series: 3, repeticiones: 12, descripcion: 'Realizar sentadillas controladas.' },
        { nombre: 'Ejercicio 2', series: 2, repeticiones: 15, descripcion: 'Ejercicios de estiramiento de brazos.' }
      ]
    }
  ];

  constructor(private router: Router) {}

  seleccionarPlan(plan: any) {
    this.router.navigate(['plan-rehabilitacion-medico', { plan: JSON.stringify(plan) }]);
  }

  crearNuevoPlan() {
    const nuevoPlan = {
      nombre: 'Nuevo Plan de Rehabilitación',
      fecha: new Date(),
      ejercicios: []
    };
    this.router.navigate(['plan-rehabilitacion-medico', { plan: JSON.stringify(nuevoPlan) }]);
  }
}
