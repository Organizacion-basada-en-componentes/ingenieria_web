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
    {
      tipo: 'Alerta Médica',
      fecha: '2024-12-07',
      descripcion: 'Paciente con fiebre alta persistente.',
      dni: '12345678A'
    },
    {
      tipo: 'Alerta Administrativa',
      fecha: '2024-12-06',
      descripcion: 'Paciente con documentos incompletos.',
      dni: '87654321B'
    },
    {
      tipo: 'Alerta Urgente',
      fecha: '2024-12-05',
      descripcion: 'Paciente en estado crítico. Necesita intervención inmediata.',
      dni: '11223344C'
    },
    {
      tipo: 'Alerta de Seguimiento',
      fecha: '2024-12-04',
      descripcion: 'Revisión pendiente de paciente.',
      dni: '44556677D'
    }
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
