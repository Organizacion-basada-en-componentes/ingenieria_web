import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { SelectedPatientService } from './selected-patient.service';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isAuthenticated$: Observable<boolean> | undefined;
  userType$: Observable<string | null> | undefined;
  selectedPatient: any = null;
  constructor(
    private authService: AuthService,
    private selectedPatientService: SelectedPatientService // Inyecta el servicio
  ) {}

  ngOnInit(): void {
    this.isAuthenticated$ = this.authService.isAuthenticated$;
    this.userType$ = this.authService.userType$;

    this.selectedPatientService.getPatient().subscribe(patient => {
      this.selectedPatient = patient;
      });
  }

  logout(): void {
    this.authService.logout();
  }
}
