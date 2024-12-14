import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, switchMap } from 'rxjs';
import { PacienteService } from './paciente.service';

@Injectable({
  providedIn: 'root'
})
export class PedirCitaService {
  private baseUrl = 'http://localhost:8080'; // URL base de la API

  constructor(private http: HttpClient, private pacienteService: PacienteService) {}

  // Método para crear una cita
  createCita(cita: any): Observable<any> {
    return this.pacienteService.paciente$.pipe(
      switchMap((paciente) => {
        if (!paciente || !paciente.id) {
          throw new Error('No se pudo obtener el ID del paciente');
        }

        // Realiza la solicitud al backend usando el ID del paciente
        const pacienteId = paciente.id;
        const url = `${this.baseUrl}/citas/${pacienteId}`;
        return this.http.post(url, cita);
      })
    );
  }

  // Método para obtener citas del paciente
  getCitas(): Observable<any> {
    return this.pacienteService.paciente$.pipe(
      switchMap((paciente) => {
        if (!paciente || !paciente.id) {
          throw new Error('No se pudo obtener el ID del paciente');
        }

        // Realiza la solicitud al backend usando el ID del paciente
        const pacienteId = paciente.id;
        const url = `${this.baseUrl}/citas/${pacienteId}`;
        return this.http.get(url);
      })
    );
  }
}
