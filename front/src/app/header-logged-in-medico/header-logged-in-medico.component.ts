import { Component, Inject } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-logged-in-medico',
  standalone: false,
  
  templateUrl: './header-logged-in-medico.component.html',
  styleUrl: './header-logged-in-medico.component.css'
})
export class HeaderLoggedInMedicoComponent {
    
 
    constructor(private router: Router,@Inject(AuthService) private authService: AuthService) {}
  
    logout(): void {
      this.authService.logout();
      this.router.navigate(['/']);

    }

}
