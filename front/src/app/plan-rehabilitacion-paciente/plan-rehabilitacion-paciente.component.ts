import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-plan-rehabilitacion-paciente',
  standalone: false,
  templateUrl: './plan-rehabilitacion-paciente.component.html',
  styleUrls: ['./plan-rehabilitacion-paciente.component.css']
})
export class PlanRehabilitacionPacienteComponent implements OnInit {
  plan = {
    nombre: 'Plan de Rehabilitación de Rodilla',
    fecha: new Date(),
    ejercicios: [
      { nombre: 'Sentadillas', series: 3, repeticiones: 12 },
      { nombre: 'Elevaciones de pierna', series: 3, repeticiones: 15 },
      { nombre: 'Flexiones de rodilla', series: 3, repeticiones: 10 }
    ]
  };

  constructor() { }

  ngOnInit(): void {
  }

}
