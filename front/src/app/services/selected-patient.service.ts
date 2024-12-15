import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SelectedPatientService {

  // Creamos un BehaviorSubject que tendrá un valor inicial que se obtiene de localStorage
  private selectedPatientSubject = new BehaviorSubject<any | null>(null);

  selectedPatient$ = this.selectedPatientSubject.asObservable();
  constructor() {
    const savedPatient = localStorage.getItem('selectedPatient');
    if (savedPatient) {
      this.selectedPatientSubject.next(JSON.parse(savedPatient));
    }
  }

  // Método para obtener el Observable que los componentes pueden suscribirse
  getPatient(): Observable<any> {
    return this.selectedPatientSubject.asObservable();
  }
  
  // Método para establecer el paciente, que también guarda en localStorage y emite el cambio
  setPatient(patient: any): void {
    // Guardamos el paciente en el BehaviorSubject
    this.selectedPatientSubject.next(patient);
    // También lo guardamos en localStorage para persistir entre recargas
    localStorage.setItem('selectedPatient', JSON.stringify(patient));
    console.log('Paciente guardado:', patient);
  }
  unsetPatient(): void {
    this.selectedPatientSubject.next(null);
    localStorage.removeItem('selectedPatient');
  }

}
