import { Component, OnInit } from '@angular/core';
import { PacienteService } from '../../services/paciente.service';

@Component({
  selector: 'app-home-paciente',
  standalone: false,
  templateUrl: './home-paciente.component.html',
  styleUrls: ['./home-paciente.component.css']
})
export class HomePacienteComponent implements OnInit {
  paciente: any = null; // Datos del paciente
  medico: any = null;   // Datos del médico

  constructor(private pacienteService: PacienteService) {}

  ngOnInit(): void {
    // Cargar datos del paciente al inicializar el componente
    this.pacienteService.loadPaciente().subscribe({
      next: (paciente) => {
        this.paciente = paciente;

        // Si el paciente se cargó correctamente, obtener su médico
        if (paciente) {
          this.pacienteService.getMedico().subscribe({
            next: (medico) => {
              this.medico = medico;
              console.log('Médico cargado correctamente:', medico);
            },
            error: (err) => {
              console.error('Error al obtener los datos del médico:', err);
            }
          });
        }
      },
      error: (err) => {
        console.error('Error al cargar los datos del paciente:', err);
      }
    });
  }
}
