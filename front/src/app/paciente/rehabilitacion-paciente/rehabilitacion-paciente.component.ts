import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  // Importa Router
import { PacienteService } from '../../services/paciente.service';
import { catchError, of, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-rehabilitacion-paciente',
  standalone: false,
  templateUrl: './rehabilitacion-paciente.component.html',
  styleUrls: ['./rehabilitacion-paciente.component.css']
})
export class RehabilitacionPacienteComponent implements OnInit {

  constructor(private router: Router,private pacienteService: PacienteService,private http: HttpClient) {}  // Inyecta el Router
  paciente: any = null; // Datos del paciente
  private baseUrl: string = 'http://localhost:8080/planes';
  planes: any[] = [];
  ngOnInit(): void {
    // Cargar datos del paciente al inicializar el componente
    this.pacienteService.loadPaciente().subscribe({
      next: (paciente) => {
        this.paciente = paciente;
        console.log('Paciente cargado:', paciente); // Debugging
        this.getPlanes(); // Llamar a getPlanes solo si el paciente tiene ID
      },
      error: (err) => {
        console.error('Error al cargar los datos del paciente:', err);
      }
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


  // Método para navegar con el plan seleccionado
  seleccionarPlan(plan: any) {
    // Navegar con un parámetro de ruta (en este caso, pasamos un identificador o un objeto serializado)
    this.router.navigate(['plan-rehabilitacion-paciente', { plan: JSON.stringify(plan) }]);
  }
}
