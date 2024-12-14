import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PedirCitaService } from '../../services/citas.service'; // Servicio para las citas
import { PacienteService } from '../../services/paciente.service'; // Servicio para cargar el paciente

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
    private pacienteService: PacienteService // Inyectar el servicio de paciente
  ) {}

  ngOnInit(): void {
    // Inicializar formulario
    this.pedirCitaForm = this.fb.group({
      fecha: ['', Validators.required],
      hora: ['', Validators.required],
      motivo: ['', [Validators.required, Validators.minLength(5)]],
    });

    // Cargar el paciente y obtener el ID del médico
    this.pacienteService.loadPaciente().subscribe(
      (paciente) => {
        if (paciente && paciente.id) {
          this.pacienteId = paciente.id;

          // Obtener el ID del médico asociado al paciente
          this.pacienteService.getMedico().subscribe(
            (medico) => {
              if (medico && medico.id) {
                this.medicoId = medico.id;  // Guardar el ID del médico
                this.loadCitas(); // Cargar citas una vez que tenemos el médico
              } else {
                console.error('No se encontró el médico asociado');
              }
            },
            (error) => {
              console.error('Error al obtener el ID del médico:', error);
            }
          );
        } else {
          console.error('Paciente no encontrado');
        }
      },
      (error) => {
        console.error('Error al cargar el paciente:', error);
      }
    );
  }

  // Método para cargar las citas del paciente
  loadCitas(): void {
    if (!this.pacienteId) {
      console.error('No se pudo obtener el ID del paciente');
      return;
    }
  
    this.loading = true;
    this.pedirCitaService.getCitas().subscribe(
      (citas) => {
        console.log('Citas recibidas:', citas); // Agrega este log para ver cómo llega la respuesta
  
        // Si citas es un objeto en vez de un arreglo, lo convertimos en un arreglo
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

    // Crear el objeto de la cita, incluyendo el ID del médico y paciente
    const nuevaCita = {
      fechaHora: fechaHoraISO,  // La fecha en formato ISO
      motivo: this.pedirCitaForm.value.motivo,
      medico: { id: this.medicoId },  // ID del médico obtenido
      paciente: { id: this.pacienteId },  // ID del paciente
    };

    // Crear la cita usando el servicio
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
