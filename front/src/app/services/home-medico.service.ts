import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class HomeMedicoService {
  private baseUrl = 'http://localhost:8080'; // URL base de la API
  private urlMedico = `${this.baseUrl}/medicos/usuario`;

  // BehaviorSubject para almacenar y compartir el objeto del médico
  private medicoSubject = new BehaviorSubject<any>(null);

  // Observable público para que otros componentes se suscriban
  public medico$ = this.medicoSubject.asObservable();

  constructor(private http: HttpClient) {}

  // Método para obtener el ID del usuario desde el token
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
  

  // Método para obtener y guardar la información del médico
  loadMedico(): Observable<any> {
    const usuarioId = this.getUsuarioIdFromToken();
    if (!usuarioId) {
      console.error('No se pudo obtener el usuario ID del token');
      this.medicoSubject.next(null); // Limpia el estado
      return of(null); // Devuelve un observable con `null`
    }
  
    return this.http.get<any>(`${this.urlMedico}/${usuarioId}`).pipe(
      tap(medico => {
        this.medicoSubject.next(medico); // Actualiza el BehaviorSubject
      }),
      catchError(error => {
        console.error('Error al cargar el médico:', error);
        this.medicoSubject.next(null); // Limpia el estado en caso de error
        return of(null); // Devuelve un observable con `null`
      })
    );
  }
  

  // Método para obtener pacientes asociados al médico
  getPacientes(): Observable<any[]> {
    return this.medico$.pipe(
      switchMap(medico => {
        if (!medico || !medico.id) {
          console.error('No se pudo obtener el ID del médico');
          return of([]); // Devuelve un Observable de un array vacío en caso de error
        }

        const medicoID = medico.id;
        return this.http.get<any[]>(`${this.baseUrl}/medicos/${medicoID}/pacientes`);
      })
    );
  }
}
