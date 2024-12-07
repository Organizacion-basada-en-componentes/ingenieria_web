import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { SelectedPatientService } from '../services/selected-patient.service';

@Component({
  selector: 'app-progreso',
  standalone: false,
  templateUrl: './progreso.component.html',
  styleUrls: ['./progreso.component.css']
})
export class ProgresoComponent implements OnInit {
  userType: 'paciente' | 'medico' | null = null;
  selectedPatient: any = null;

  constructor(
    private authService: AuthService,
    private selectedPatientService: SelectedPatientService
  ) {}

  ngOnInit(): void {
    // Obtener el tipo de usuario del servicio AuthService
    this.userType = this.authService.getUserType();

    if (this.userType === 'medico') {
      // Suscribirse al paciente seleccionado si el usuario es médico
      this.selectedPatientService.getPatient().subscribe(patient => {
        this.selectedPatient = patient;
      });
    }
  }
}
