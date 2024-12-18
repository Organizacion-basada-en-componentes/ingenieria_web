import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { concatMap, tap } from 'rxjs/operators';
import { LoadingService } from './loading.service';  // Asegúrate de importar el LoadingService

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private userTypeSubject = new BehaviorSubject<'paciente' | 'medico' | null>(null);
  private tokenKey = 'authToken';

  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();
  userType$ = this.userTypeSubject.asObservable();

  constructor(
    private http: HttpClient,
    private loadingService: LoadingService // Inyección del servicio de carga
  ) {
    // Recuperar el estado de autenticación y tipo de usuario desde localStorage
    const token = localStorage.getItem(this.tokenKey);
    const savedUserType = localStorage.getItem('userType');

    if (token && savedUserType) {
      this.isAuthenticatedSubject.next(true);
      this.userTypeSubject.next(savedUserType as 'paciente' | 'medico');
    } else {
      this.isAuthenticatedSubject.next(false);
      this.userTypeSubject.next(null);
    }
  }

  // Método para loguearse con usuario y contraseña (en el backend)
  login(username: string, password: string): Observable<any> {
    this.loadingService.show(); // Mostrar loading antes de iniciar la solicitud
    return this.http.post<{ token: string }>('http://localhost:8080/auth/login', { username, password }).pipe(
      tap((response) => {
        localStorage.setItem(this.tokenKey, response.token);
        this.isAuthenticatedSubject.next(true);
        const userType = this.getUserTypeFromToken(response.token);
        this.userTypeSubject.next(userType);
        localStorage.setItem('userType', userType);
      }),
      tap(() => this.loadingService.hide()), // Ocultar loading al finalizar
      // Si hay un error, asegúrate de ocultar el loading
      tap({ error: () => this.loadingService.hide() })
    );
  }

  // Método de registro de usuario
  register(data: any): Observable<any> {
    this.loadingService.show(); // Mostrar loading antes de iniciar la solicitud
    return this.http.post<{ token: string }>('http://localhost:8080/auth/register', data, {
      headers: { 'Content-Type': 'application/json' }
    }).pipe(
      tap((response) => {
        // Al recibir la respuesta con el token
        const token = response.token;
        localStorage.setItem(this.tokenKey, token);

        // Extraemos el tipo de usuario desde el token
        const userType = this.getUserTypeFromToken(token);
        this.userTypeSubject.next(userType);
        localStorage.setItem('userType', userType);

        // Actualizamos el estado de autenticación
        this.isAuthenticatedSubject.next(true);

        // Si el usuario es paciente, sincronizamos con el backend
        if (userType === 'paciente') {
          this.http.post('http://localhost:8080/chats/sincronizar', {}, {
            headers: { 'Content-Type': 'application/json' }
          }).subscribe();
        }
      }),
      tap(() => this.loadingService.hide()), // Ocultar loading al finalizar
      // Si hay un error, asegúrate de ocultar el loading
      tap({ error: () => this.loadingService.hide() })
    );
  }

  // Método para cerrar sesión
  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem('userType');
    this.isAuthenticatedSubject.next(false);
    this.userTypeSubject.next(null);
  }

  // Obtener token de localStorage
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Método para obtener el tipo de usuario (paciente/medico) desde el token
  getUserType(): 'paciente' | 'medico' | null {
    return this.userTypeSubject.value;
  }

  // Método para obtener el tipo de usuario decodificando el token (usando JWT)
  private getUserTypeFromToken(token: string): 'paciente' | 'medico' {
    const payload = token.split('.')[1];
    const data = JSON.parse(atob(payload));
    // Pasamos a minusculas el rol
    return data.role.toLowerCase() as 'paciente' | 'medico';
  }

  // Comprobar si el usuario está autenticado
  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
