import { Component, OnInit } from '@angular/core';
import { SelectedPatientService } from '../selected-patient.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-selected-patient',
  standalone: false,
  templateUrl: './selected-patient.component.html',
  styleUrls: ['./selected-patient.component.css']
})
export class SelectedPatientComponent implements OnInit {
  selectedPatient: any = null;

  constructor(private selectedPatientService: SelectedPatientService,
     private router: Router
  ) {}

  ngOnInit(): void {
    // Nos suscribimos al servicio para obtener los cambios del paciente seleccionado
    this.selectedPatientService.getPatient().subscribe(patient => {
      this.selectedPatient = patient;
      });
  
  }
}
