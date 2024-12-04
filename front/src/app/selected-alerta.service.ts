import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SelectedAlertaService {
  private selectedAlerta: any = null;
  constructor() { }
  setAlerta(alerta: any): void {
    this.selectedAlerta = alerta;
  }
  getAlerta(): any {
    return this.selectedAlerta;
  }
}
