import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, of, switchMap, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {
  private baseUrl = 'http://localhost:8080'; // URL base de la API
  private urlpaciente = `${this.baseUrl}/pacientes/usuario`;

  // BehaviorSubject para almacenar y compartir el objeto del médico
  private pacienteSubject = new BehaviorSubject<any>(null);

  // Observable público para que otros componentes se suscriban
  public paciente$ = this.pacienteSubject.asObservable();

  constructor(private http: HttpClient) {}

  private getUsuarioIdFromToken(): string | null {
    const token = localStorage.getItem('authToken');
    if (!token) {
      console.error('No se encontró el token en localStorage');
      return null;
    }
  
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      console.log('Payload decodificado del token:', payload);
      if (!payload.id) {
        console.error('El token no contiene un ID');
        return null;
      }
      return payload.id;
    } catch (error) {
      console.error('Error al decodificar el token:', error);
      return null;
    }
  }
  // Método para obtener y guardar la información del paciente
  loadPaciente(): Observable<any> {
    const usuarioId = this.getUsuarioIdFromToken();
    if (!usuarioId) {
      console.error('No se pudo obtener el usuario ID del token');
      this.pacienteSubject.next(null); // Limpia el estado
      return of(null); // Devuelve un observable con `null`
    }
  
    return this.http.get<any>(`${this.urlpaciente}/${usuarioId}`).pipe(
      tap(medico => {
        this.pacienteSubject.next(medico); // Actualiza el BehaviorSubject
      }),
      catchError(error => {
        console.error('Error al cargar el médico:', error);
        this.pacienteSubject.next(null); // Limpia el estado en caso de error
        return of(null); // Devuelve un observable con `null`
      })
    );
  }
  getPacienteById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/pacientes/${id}`);
  }

  getMedico(): Observable<any> {
    return this.paciente$.pipe(
      switchMap((paciente) => {
        console.log('Paciente recibido en getMedico:', paciente); // Asegúrate de ver lo que contiene el paciente
        if (!paciente || !paciente.id) {
          console.error('No se pudo obtener el ID del paciente');
          return of(null); // Devuelve un Observable con `null` en caso de error
        }
  
        const pacienteID = paciente.id;
        console.log('ID del paciente:', pacienteID); // Verifica que se esté obteniendo el ID
  
        // Primera petición: obtener el ID del médico asociado al paciente
        return this.http.get<number>(`${this.baseUrl}/pacientes/${pacienteID}/medico`).pipe(
          switchMap((medicoID: number) => {
            if (!medicoID) {
              console.error('No se encontró un ID de médico');
              return of(null); // Devuelve un Observable con `null` si no hay médico
            }
  
            console.log('ID del médico:', medicoID); // Verifica que se esté obteniendo el ID del médico
  
            // Segunda petición: obtener los detalles del médico usando su ID
            return this.http.get<any>(`${this.baseUrl}/medicos/${medicoID}`);
          })
        );
      }),
      catchError((error) => {
        console.error('Error al obtener los datos del médico:', error);
        return of(null); // Manejo de errores: devolver un Observable con `null`
      })
    );
  }
  
      


}
