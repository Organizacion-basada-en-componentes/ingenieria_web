import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-plan-rehabilitacion-medico',
  standalone: false,
  templateUrl: './plan-rehabilitacion-medico.component.html',
  styleUrls: ['./plan-rehabilitacion-medico.component.css']
})
export class PlanRehabilitacionMedicoComponent implements OnInit {
  plan: any = {
    nombre: '',
    fecha: new Date(),
    ejercicios: []
  };

  nuevoEjercicio = {
    nombre: '',
    series: 0,
    repeticiones: 0,
    descripcion: ''
  };

  ejercicioEditando: any = null; // Ejercicio en proceso de edición
  ejercicioEditandoIndex: number | null = null; // Índice del ejercicio en edición

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const planData = params['plan'];
      if (planData) {
        this.plan = JSON.parse(planData);
      }
    });
  }

  agregarEjercicio() {
    if (this.nuevoEjercicio.nombre && this.nuevoEjercicio.series > 0 && this.nuevoEjercicio.repeticiones > 0 && this.nuevoEjercicio.descripcion) {
      this.plan.ejercicios.push({ ...this.nuevoEjercicio });
      this.nuevoEjercicio = { nombre: '', series: 0, repeticiones: 0, descripcion: '' };
    } else {
      alert('Por favor, completa todos los campos del ejercicio.');
    }
  }

  eliminarEjercicio(index: number) {
    this.plan.ejercicios.splice(index, 1);
  }

  editarEjercicio(index: number) {
    this.ejercicioEditandoIndex = index; // Actualiza el índice del ejercicio en edición
    this.ejercicioEditando = { ...this.plan.ejercicios[index] }; // Copiar el ejercicio
  }

  guardarEdicionEjercicio() {
    if (this.ejercicioEditandoIndex !== null && this.ejercicioEditando) {
      // Actualiza el ejercicio en el array original
      this.plan.ejercicios[this.ejercicioEditandoIndex] = { ...this.ejercicioEditando };
      this.cancelarEdicion(); // Limpiar el estado de edición
    } else {
      alert('No se está editando ningún ejercicio.');
    }
  }

  cancelarEdicion() {
    this.ejercicioEditando = null;
    this.ejercicioEditandoIndex = null;
  }

  guardarPlan() {
    console.log('Plan guardado:', this.plan);
    alert('Plan de rehabilitación guardado con éxito.');
    this.router.navigate(['rehabilitacion-medico']);
  }
}
