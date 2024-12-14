import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HomeMedicoService {
  private baseUrl = 'http://localhost:8080'; // URL base de la API

  constructor(private http: HttpClient) {}

  // Método para obtener el ID del médico desde el token
  private getMedicoIdFromToken(): string | null {
  const token = localStorage.getItem('authToken');
  if (!token) {
    console.error('No se encontró el token en localStorage');
    return null;
  }

  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    console.log('Payload del token:', payload); // Verifica qué datos tiene el token
    return payload.id || null; // Utiliza 'id' como el ID del médico
  } catch (error) {
    console.error('Error al decodificar el token:', error);
    return null;
  }
}


  // Método para obtener pacientes asociados al médico
  getPacientes(): Observable<any[]> {
    const medicoId = this.getMedicoIdFromToken();
    if (!medicoId) {
      console.error('ID del médico no disponible');
      return new Observable(); // Devuelve un observable vacío en caso de que no haya ID
    }

    return this.http.get<any[]>(`${this.baseUrl}/medicos/${medicoId}/pacientes`);
  }
}
