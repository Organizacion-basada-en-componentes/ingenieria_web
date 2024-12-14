import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, switchMap, of, catchError } from 'rxjs';
import { PacienteService } from './paciente.service';

@Injectable({
  providedIn: 'root',
})
export class PedirCitaService {
  private baseUrl = 'http://localhost:8080'; // URL base de la API

  constructor(
    private http: HttpClient,
    private pacienteService: PacienteService // Inyectamos el servicio que maneja el paciente
  ) {}

  // Método para obtener citas del paciente
  getCitas(): Observable<any> {
    return this.pacienteService.paciente$.pipe(
      switchMap((paciente) => {
        if (!paciente || !paciente.id) {
          throw new Error('No se pudo obtener el ID del paciente');
        }

        const pacienteId = paciente.id;
        const url = `${this.baseUrl}/citas/${pacienteId}`;
        return this.http.get(url).pipe(
          catchError((error) => {
            // Si obtenemos un error 404, tomamos los datos del paciente desde el servicio de paciente
            if (error.status === 404) {
              console.warn('No se encontró el paciente en la API, usando datos del servicio');
              return of(paciente); // Devuelvo el paciente del servicio en caso de 404
            }
            // En cualquier otro error, lo lanzamos
            throw error;
          })
        );
      })
    );
  }

  // Método para crear una cita
  createCita(cita: any): Observable<any> {
    return this.pacienteService.paciente$.pipe(
      switchMap((paciente) => {
        if (!paciente || !paciente.id) {
          throw new Error('No se pudo obtener el ID del paciente');
        }

        const pacienteId = paciente.id;
        const url = `${this.baseUrl}/citas/${pacienteId}`;
        return this.http.post(url, cita);
      })
    );
  }
}
