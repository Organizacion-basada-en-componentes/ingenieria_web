import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PacienteService } from '../../services/paciente.service'; // Servicio para cargar el paciente
import { SelectedPatientService } from '../../services/selected-patient.service'; // Servicio para obtener el paciente seleccionado
import { HomeMedicoService } from '../../services/home-medico.service';
import { HttpClient } from '@angular/common/http';

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
  public usertype = localStorage.getItem('userType'); // Tipo de usuario (paciente o médico)

  public idPaciente: number = 0;
  public idMedico: number = 0;
  urlGetCitas = 'http://localhost:8080/citas/paciente/';
  urlPostCita = 'http://localhost:8080/citas';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private homeMedicoService: HomeMedicoService,
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
    console.log('Tipo de usuario:', this.usertype);

    if (this.usertype === 'paciente') {
      // Cargar datos del paciente al inicializar el componente
      this.pacienteService.loadPaciente().subscribe({
        next: (paciente) => {
          this.idPaciente = paciente.id; // Obtiene el ID del paciente
          console.log('Paciente cargado:', this.idPaciente); // Debugging
          this.loadCitas(); // Llamar a loadCitas solo si el paciente tiene ID
        },
        error: (err: any) => {
          console.error('Error al cargar los datos del paciente:', err);
        }
      });
      //cargamos el id del medico del mismo servicio
      this.pacienteService.getMedico().subscribe({
        next: (medico) => {
          this.idMedico = medico.id;
          console.log('Medico cargado:', this.idMedico); // Debugging
        },
        error: (err: any) => {
          console.error('Error al cargar los datos del medico:', err);
        }
      });
    } else if (this.usertype === 'medico') {
      this.selectedPatientService.getPatient().subscribe({
        next: (patient) => {
          console.log('Paciente seleccionado cargado:', patient);
          this.idPaciente = patient.id;
          this.loadCitas();
        },
        error: (err) => {
          console.error('Error al obtener paciente seleccionado:', err);
        }
      });
      //cargamos el id del medico del servicio home-medico
      this.homeMedicoService.loadMedico().subscribe({
        next: (medico) => {
          if (medico) {
            console.log('Médico cargado:', medico);
            this.idMedico = medico.id;
          } else {
            console.error('No se pudo cargar el médico.');
            // Lógica para manejar este caso (redirigir, mostrar mensaje, etc.)
          }
        },
        error: (err) => {
          console.error('Error al cargar el médico:', err);
        },
      });
    } else {
      console.error('Tipo de usuario no válido.');
    }
  }

  // Método para cargar las citas del paciente
  loadCitas(): void {
    //coger las citas del paciente desde su endpoint
    this.loading = true;
    this.http.get<any[]>(`${this.urlGetCitas}${this.idPaciente}`).subscribe({
      next: (citas) => {
        this.citas = citas;
        console.log('Citas cargadas:', this.citas); // Debugging
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar las citas:', err);
        this.loading = false;
      },
    });
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
      medico: { id: this.idMedico }, // ID del médico obtenido
      paciente: { id: this.idPaciente }, // ID del paciente
    };
    //enviar la cita al endpoint de citas
    this.http.post(`${this.urlPostCita}/${this.idPaciente}`, nuevaCita).subscribe({
      next: (cita) => {
        console.log('Cita pedida:', cita); // Debugging
        this.loadCitas(); // Recargar la lista de citas
        this.pedirCitaForm.reset(); // Limpiar el formulario
      },
      error: (err) => {
        console.error('Error al pedir la cita:', err);
      },
    });
   
  }
}
