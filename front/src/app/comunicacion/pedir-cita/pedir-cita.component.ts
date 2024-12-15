import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PedirCitaService } from '../../services/citas.service'; // Servicio para las citas
import { PacienteService } from '../../services/paciente.service'; // Servicio para cargar el paciente
import { SelectedPatientService } from '../../services/selected-patient.service'; // Servicio para obtener el paciente seleccionado

@Component({
  selector: 'app-pedir-cita',
  standalone: false,
  templateUrl: './pedir-cita.component.html',
  styleUrls: ['./pedir-cita.component.css'],
})
export class PedirCitaComponent implements OnInit {
  pedirCitaForm!: FormGroup;
  citas: any[] = [];
  loading = false;
  pacienteId!: number;
  medicoId!: number;

  constructor(
    private fb: FormBuilder,
    private pedirCitaService: PedirCitaService,
    private pacienteService: PacienteService, // Inyectar el servicio de paciente
    private selectedPatientService: SelectedPatientService // Inyectamos el servicio del paciente seleccionado
  ) {}

  ngOnInit(): void {
    // Inicializar formulario
    this.pedirCitaForm = this.fb.group({
      fecha: ['', Validators.required],
      hora: ['', Validators.required],
      motivo: ['', [Validators.required, Validators.minLength(5)]],
    });

    // Intentamos obtener el paciente desde el servicio SelectedPatientService
    this.selectedPatientService.getPatient().subscribe(
      (selectedPatient) => {
        if (selectedPatient && selectedPatient.id) {
          this.pacienteId = selectedPatient.id; // Asignar ID del paciente desde el SelectedPatientService
          console.log('Paciente encontrado desde SelectedPatientService:', this.pacienteId);

          // Ahora intentamos cargar el médico asociado a este paciente
          this.pacienteService.getMedico().subscribe(
            (medico) => {
              if (medico && medico.id) {
                this.medicoId = medico.id; // Asignar ID del médico
                this.loadCitas(); // Cargar citas después de obtener el médico
              } else {
                console.error('No se encontró el médico asociado');
              }
            },
            (error) => {
              console.error('Error al obtener el médico:', error);
            }
          );
        } else {
          console.error('No se encontró el paciente desde SelectedPatientService');
        }
      },
      (error) => {
        console.error('Error al obtener el paciente desde SelectedPatientService:', error);
      }
    );
  }

  // Método para cargar las citas del paciente
  loadCitas(): void {
    this.loading = true;
    this.pedirCitaService.getCitas().subscribe(
      (citas) => {
        console.log('Citas recibidas:', citas);
        this.citas = Array.isArray(citas) ? citas : [citas];
        this.loading = false;
      },
      (error) => {
        console.error('Error al cargar las citas:', error);
        this.loading = false;
      }
    );
  }

  // Método para enviar el formulario y pedir una cita
  onSubmit(): void {
    if (this.pedirCitaForm.invalid) {
      return;
    }

    const fechaHoraISO = `${this.pedirCitaForm.value.fecha}T${this.pedirCitaForm.value.hora}`;

    const nuevaCita = {
      fechaHora: fechaHoraISO, // La fecha en formato ISO
      motivo: this.pedirCitaForm.value.motivo,
      medico: { id: this.medicoId }, // ID del médico obtenido
      paciente: { id: this.pacienteId }, // ID del paciente
    };

    this.pedirCitaService.createCita(nuevaCita).subscribe(
      (response) => {
        console.log('Cita creada exitosamente:', response);
        this.pedirCitaForm.reset();
        this.loadCitas(); // Recargar citas después de crear la nueva cita
      },
      (error) => {
        console.error('Error al crear la cita:', error);
      }
    );
  }
}
