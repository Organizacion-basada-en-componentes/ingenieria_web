import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-plan-rehabilitacion-paciente',
  standalone: false,
  templateUrl: './plan-rehabilitacion-paciente.component.html',
  styleUrls: ['./plan-rehabilitacion-paciente.component.css']
})
export class PlanRehabilitacionPacienteComponent implements OnInit {
  // Definir el plan como un objeto vacío por defecto
  plan: any = {
    nombre: 'Nombre del plan',
    fecha: new Date(),
    ejercicios: []
  };

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Acceder al parámetro de ruta 'plan', deserializarlo y asignarlo al plan
    this.route.params.subscribe(params => {
      const planData = params['plan'];
      if (planData) {
        this.plan = JSON.parse(planData);  // Deserializa el objeto JSON recibido
      }
    });
  }
}
