import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectedPatientService } from '../../services/selected-patient.service';
import { HttpClient } from '@angular/common/http';
import { LoadingService } from '../../services/loading.service';

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
    descripcion: '',
    completado: false,
    comentario: ''

  };

  ejercicioEditando: any = null;
  ejercicioEditandoIndex: number | null = null;

  paciente: any = null;
  urlBase = 'http://localhost:8080/planes';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private selectedPatientService: SelectedPatientService,
    private http: HttpClient,
    private loadingService: LoadingService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const planData = params['plan'];
      if (planData) {
        this.plan = JSON.parse(planData);
      }
    });

    // Seleccionar al paciente
    this.selectedPatientService.getPatient().subscribe({
      next: (patient) => {
        this.paciente = patient;
        console.log('Paciente cargado:', this.paciente); // Debugging
        if (!this.paciente || !this.paciente.id) {
          console.error('No se pudo cargar el paciente o falta el ID.');
        }
      },
      error: (err) => {
        console.error('Error al obtener el paciente:', err);
      },
    });
  }

  agregarEjercicio() {
    if (this.nuevoEjercicio.nombre && this.nuevoEjercicio.series > 0 && this.nuevoEjercicio.repeticiones > 0 && this.nuevoEjercicio.descripcion) {
      this.plan.ejercicios.push({ ...this.nuevoEjercicio });
      console.log('Ejercicio añadido:', this.nuevoEjercicio); // Debugging
      this.nuevoEjercicio = { nombre: '', series: 0, repeticiones: 0, descripcion: '', completado: false, comentario: '' };
    } else {
      alert('Por favor, completa todos los campos del ejercicio.');
    }
  }

  eliminarEjercicio(index: number) {
    this.plan.ejercicios.splice(index, 1);
    console.log('Ejercicio eliminado en índice:', index); // Debugging
  }

  editarEjercicio(index: number) {
    this.ejercicioEditandoIndex = index;
    this.ejercicioEditando = { ...this.plan.ejercicios[index] };
    console.log('Ejercicio editando:', this.ejercicioEditando); // Debugging
  }

  guardarEdicionEjercicio() {
    if (this.ejercicioEditandoIndex !== null && this.ejercicioEditando) {
      this.plan.ejercicios[this.ejercicioEditandoIndex] = { ...this.ejercicioEditando };
      this.cancelarEdicion();
      console.log('Ejercicio editado:', this.plan.ejercicios[this.ejercicioEditandoIndex]); // Debugging
    } else {
      alert('No se está editando ningún ejercicio.');
    }
  }

  cancelarEdicion() {
    this.ejercicioEditando = null;
    this.ejercicioEditandoIndex = null;
  }

  guardarPlan() {
    this.loadingService.show();
    if (!this.paciente || !this.paciente.id) {
      alert('No se puede guardar el plan porque no se ha seleccionado un paciente válido.');
      return;
    }

    // Verificación de la URL
    const url = `${this.urlBase}/${this.paciente.id}`;
    console.log('URL para guardar el plan:', url); // Debugging

    this.http.post(url, this.plan).subscribe({
      next: (response) => {
        console.log('Plan guardado exitosamente:', response);
        this.loadingService.hide();
        this.router.navigate(['rehabilitacion-medico']);
        alert('Plan de rehabilitación guardado con éxito.');

      },
      error: (error) => {
        console.error('Error al guardar el plan:', error);
        this.loadingService.hide();
        alert('Hubo un error al guardar el plan. Por favor, intenta nuevamente.');
      }
    });
  }
}
