import { Injectable } from '@angular/core';

@Injectable({

  providedIn: 'root',
})
export class AuthService {
  private isAuthenticated = false;
  private userType: 'paciente' | 'medico' | null = null;

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  getUserType(): 'paciente' | 'medico' | null {
    return this.userType;
  }

  login(userType: 'paciente' | 'medico'): void {
    this.isAuthenticated = true;
    this.userType = userType;
  }

  logout(): void {
    this.isAuthenticated = false;
    this.userType = null;
  }
}
