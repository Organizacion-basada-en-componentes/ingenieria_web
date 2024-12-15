import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  private _loading = new BehaviorSubject<boolean>(false);
  public readonly loadingStatus = this._loading.asObservable();

  show(): void {
    this._loading.next(true);  // Muestra el loading
  }

  hide(): void {
    // Introduce un retraso de 1 segundo (1000ms) antes de ocultar el loading
    setTimeout(() => {
      this._loading.next(false);  // Oculta el loading después del retraso
    }, 500);  // Puedes ajustar el tiempo aquí
  }
}
