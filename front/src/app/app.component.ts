import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private authService: AuthService) {}
  isAuthenticated$: Observable<boolean> | undefined;
  userType$: Observable<string | null> | undefined;

  

  ngOnInit(): void {
    this.isAuthenticated$ = this.authService.isAuthenticated$;
    this.userType$ = this.authService.userType$;
  }

  logout(): void {
    this.authService.logout();
  }
}
