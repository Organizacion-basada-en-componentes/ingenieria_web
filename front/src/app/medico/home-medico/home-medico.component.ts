import { Component, OnInit, OnDestroy } from '@angular/core';
import { SelectedPatientService } from '../../services/selected-patient.service';
import { SelectedAlertaService } from '../../services/selected-alerta.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home-medico',
  standalone: false,
  templateUrl: './home-medico.component.html',
  styleUrls: ['./home-medico.component.css']
})
export class HomeMedicoComponent implements OnInit{
 

  constructor(
    private selectedPatientService: SelectedPatientService,
    private alertaService: SelectedAlertaService,
    private router: Router
  ) {}
  nombre = 'Dr. Diego';
  pacientes = [
    { nombre: 'Juan Pérez', edad: 45, ultimaConsulta: new Date('2024-11-15') },
    { nombre: 'María López', edad: 34, ultimaConsulta: new Date('2024-11-10') },
    { nombre: 'Pedro García', edad: 60, ultimaConsulta: new Date('2024-11-05') },
  ];

  alertas = [
    { titulo: 'Consulta Pendiente', mensaje: 'Paciente Juan Pérez necesita una nueva cita.' },
    { titulo: 'Nuevo Mensaje', mensaje: 'Tienes un mensaje del paciente María López.' },
    { titulo: 'Alerta Crítica', mensaje: 'El paciente Pedro García tiene resultados preocupantes.' },
  ];

  ngOnInit(): void {
    console.log('HomeMedicoComponent ngOnInit');
    // Quitamos el paciente y alerta seleccionados cuando entramos al componente
    this.selectedPatientService.setPatient(null);
    this.alertaService.setAlerta(null);
    console.log(this.selectedPatientService.selectedPatient$);

  }

 

  selectPatient(paciente: any): void {
    this.selectedPatientService.setPatient(paciente);
    this.router.navigate(['/paciente-seleccionado']);
  }

  selectAlerta(alerta: any): void {
    this.alertaService.setAlerta(alerta);
    this.router.navigate(['/alerta-seleccionada']);
  }
}
