import { Component } from '@angular/core';

@Component({
  selector: 'app-header-logged-in',
  standalone: false,
  templateUrl: './header-logged-in.component.html',
  styleUrls: ['./header-logged-in.component.css']
})
export class HeaderLoggedInComponent {
  logout() {
    console.log('Cerrar sesión');
    // Implementa aquí la lógica para cerrar sesión
  }
}
