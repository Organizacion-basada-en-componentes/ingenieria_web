import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-header-logged-out',
  standalone: false,
  templateUrl: './header-logged-out.component.html',
  styleUrls: ['./header-logged-out.component.css']
})
export class HeaderLoggedOutComponent {
  constructor(private router: Router) {}

  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}
