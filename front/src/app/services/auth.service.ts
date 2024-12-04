import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private userTypeSubject = new BehaviorSubject<'paciente' | 'medico' | null>(null);

  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();
  userType$ = this.userTypeSubject.asObservable();

  constructor() {
    // Recuperar el estado de autenticación y tipo de usuario desde localStorage
    const savedAuthState = localStorage.getItem('isAuthenticated');
    const savedUserType = localStorage.getItem('userType');

    // Si existen en localStorage, establecer el estado en el BehaviorSubject
    if (savedAuthState && savedUserType) {
      this.isAuthenticatedSubject.next(savedAuthState === 'true'); // 'true' o 'false' en string
      this.userTypeSubject.next(savedUserType as 'paciente' | 'medico');
    }
  }

  // Método para comprobar si el usuario está autenticado
  isLoggedIn(): boolean {
    return this.isAuthenticatedSubject.value;
  }

  // Método para obtener el tipo de usuario
  getUserType(): 'paciente' | 'medico' | null {
    return this.userTypeSubject.value;
  }

  // Método para loguearse como 'paciente' o 'medico'
  login(userType: 'paciente' | 'medico'): void {
    this.isAuthenticatedSubject.next(true); // Usuario autenticado
    this.userTypeSubject.next(userType); // Establece el tipo de usuario

    // Guardar el estado de autenticación y tipo de usuario en localStorage
    localStorage.setItem('isAuthenticated', 'true');
    localStorage.setItem('userType', userType);
  }

  // Método para cerrar sesión
  logout(): void {
    this.isAuthenticatedSubject.next(false); // Usuario no autenticado
    this.userTypeSubject.next(null); // Sin tipo de usuario

    // Eliminar el estado de autenticación y tipo de usuario de localStorage
    localStorage.removeItem('isAuthenticated');
    localStorage.removeItem('userType');
  }
}
