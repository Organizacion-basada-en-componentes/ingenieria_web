import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeMedicoService } from '../../services/home-medico.service';
import { SelectedPatientService } from '../../services/selected-patient.service';

@Component({
  selector: 'app-home-medico',
  standalone: false,
  templateUrl: './home-medico.component.html',
  styleUrls: ['./home-medico.component.css'],
})
export class HomeMedicoComponent implements OnInit {
  nombre = '';
  id = 0;
  pacientes: any[] = [];
  medico: any; // Variable para almacenar el médico

  constructor(
    private homeMedicoService: HomeMedicoService,
    private router: Router,
    private selectedPatientService: SelectedPatientService
  ) {}

  ngOnInit(): void {
    this.selectedPatientService.setPatient(null)
    // Cargar la información del médico
    this.homeMedicoService.loadMedico().subscribe({
      next: (medico) => {
        if (medico) {
          console.log('Médico cargado:', medico);
          this.medico = medico;
          this.nombre = medico.nombre;
          this.id = medico.id;
        } else {
          console.error('No se pudo cargar el médico.');
          // Lógica para manejar este caso (redirigir, mostrar mensaje, etc.)
        }
      },
      error: (err) => {
        console.error('Error al cargar el médico:', err);
      },
    });
  
    // Cargar pacientes
    this.homeMedicoService.getPacientes().subscribe({
      next: (data) => {
        console.log('Pacientes obtenidos:', data);
        this.pacientes = data;
      },
      error: (err) => {
        console.error('Error al cargar los pacientes:', err);
      },
    });
  }
  

  selectPatient(paciente: any): void {
    // Lógica para seleccionar al paciente
    this.selectedPatientService.setPatient(paciente);
    this.router.navigate(['/paciente-seleccionado']);
  }

  // Método para calcular la edad
  calcularEdad(fechaNacimiento: string): number {
    const hoy = new Date();
    const nacimiento = new Date(fechaNacimiento);
    let edad = hoy.getFullYear() - nacimiento.getFullYear();
    const mes = hoy.getMonth() - nacimiento.getMonth();

    // Ajustar si aún no ha pasado el cumpleaños este año
    if (mes < 0 || (mes === 0 && hoy.getDate() < nacimiento.getDate())) {
      edad--;
    }

    return edad;
  }
}
