import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeMedicoService } from '../../services/home-medico.service';
import { SelectedPatientService} from '../../services/selected-patient.service'

@Component({
  selector: 'app-home-medico',
  standalone: false,
  templateUrl: './home-medico.component.html',
  styleUrls: ['./home-medico.component.css'],
})
export class HomeMedicoComponent implements OnInit {
  nombre = '';
  id=0;
  pacientes: any[] = [];

  constructor(
    private homeMedicoService: HomeMedicoService,
    private router: Router,
    private selectedPatientService: SelectedPatientService) {}

  ngOnInit(): void {
    // Obtén el nombre del médico desde el token
    const token = localStorage.getItem('authToken')!;
    const payload = JSON.parse(atob(token.split('.')[1]));
    this.id = payload.id; // Cambia 'nombre' por el campo correcto en el token

    // Carga los pacientes del médico
    this.homeMedicoService.getPacientes().subscribe(
      (data) => {
        console.log('Pacientes obtenidos:', data);  // Agrega este log para ver los pacientes obtenidos
        this.pacientes = data;
      },
      (error) => {
        console.error('Error al cargar los pacientes:', error);
      }
    );
  }

  selectPatient(paciente: any): void {
    // Lógica para seleccionar al paciente
    this.selectedPatientService.setPatient(paciente)
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