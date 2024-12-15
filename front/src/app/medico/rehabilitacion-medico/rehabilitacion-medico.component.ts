import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SelectedPatientService } from '../../services/selected-patient.service';
import { catchError, of, tap } from 'rxjs';

@Component({
  selector: 'app-rehabilitacion-medico',
  standalone: false,
  templateUrl: './rehabilitacion-medico.component.html',
  styleUrls: ['./rehabilitacion-medico.component.css']
})
export class RehabilitacionMedicoComponent implements OnInit {

  private paciente: { id: number; [key: string]: any } | null = null;
  private baseUrl: string = 'http://localhost:8080/planes';
  planes: any[] = [];

  constructor(
    private router: Router,
    private selectedPatientService: SelectedPatientService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.selectedPatientService.getPatient().subscribe({
      next: (patient) => {
        this.paciente = patient;
        if (this.paciente && this.paciente.id) {
          this.getPlanes(); // Llamar a getPlanes solo si el paciente tiene ID
        } else {
          console.error('No se pudo cargar el paciente o falta el ID.');
        }
      },
      error: (err) => {
        console.error('Error al obtener el paciente:', err);
      },
    });
  }

  getPlanes(): void {
    if (!this.paciente || !this.paciente.id) {
      console.error('El ID del paciente no está definido.');
      return;
    }

    this.http.get<any[]>(`${this.baseUrl}/paciente/${this.paciente.id}`).pipe(
      tap((planes) => {
        console.log('Planes de rehabilitación obtenidos:', planes);
        this.planes = planes;
      }),
      catchError((error) => {
        console.error('Error al cargar los planes de rehabilitación:', error);
        return of([]); // Devuelve un array vacío en caso de error
      })
    ).subscribe(); // Nos suscribimos al observable para que se ejecute
  }

  seleccionarPlan(plan: any): void {
    this.router.navigate(['plan-rehabilitacion-medico', { plan: JSON.stringify(plan) }]);
  }

  crearNuevoPlan(): void {
    const nuevoPlan = {
      nombre: 'Nuevo Plan de Rehabilitación',
      fecha: new Date(),
      ejercicios: [],
    };
    this.router.navigate(['plan-rehabilitacion-medico', { plan: JSON.stringify(nuevoPlan) }]);
  }
}
