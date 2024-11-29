
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-acceso-denegado',
  standalone: false,

  templateUrl: './acceso-denegado.component.html',
  styleUrls: ['./acceso-denegado.component.css']
})
export class AccesoDenegadoComponent {
  constructor(private router: Router) {}

  goHome() {
    this.router.navigate(['/']);
  }
}
