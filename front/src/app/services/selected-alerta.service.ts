import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SelectedAlertaService {

  // Creamos un BehaviorSubject que tendrá un valor inicial que se obtiene de localStorage
  private selectedAlertaSubject = new BehaviorSubject<any | null>(null);

  selectedAlerta$ = this.selectedAlertaSubject.asObservable();

  constructor() {
    const savedAlerta = localStorage.getItem('selectedAlerta');
    if (savedAlerta) {
      this.selectedAlertaSubject.next(JSON.parse(savedAlerta));
    }
  }

  // Método para obtener el Observable que los componentes pueden suscribirse
  getAlerta(): Observable<any> {
    return this.selectedAlertaSubject.asObservable();
  }
  
  // Método para establecer la alerta, que también guarda en localStorage y emite el cambio
  setAlerta(alerta: any): void {
    this.selectedAlertaSubject.next(alerta);
    localStorage.setItem('selectedAlerta', JSON.stringify(alerta));
  }

  // Método para deseleccionar la alerta, eliminándola también del localStorage
  unsetAlerta(): void {
    this.selectedAlertaSubject.next(null);
    localStorage.removeItem('selectedAlerta');
  }
}
