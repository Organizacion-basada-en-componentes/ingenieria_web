import { ActivatedRouteSnapshot, CanActivate, Router } from "@angular/router";
import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (this.authService.isLoggedIn()) {
      const userType = this.authService.getUserType();
      const requiredRole = route.data['role']; // Obtener el rol requerido desde la ruta

      // Verificar que el rol del usuario coincide con el requerido para esta ruta
      if (userType === requiredRole) {
        return true;
      } else {
        // Redirigir a la página de acceso denegado si no coincide el rol
        this.router.navigate(['/acceso-denegado']);
        return false;
      }
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
