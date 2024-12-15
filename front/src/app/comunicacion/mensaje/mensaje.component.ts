import { Component, OnInit } from '@angular/core';
import { MensajesService } from '../../services/mensajes.service';
import { HttpClient } from '@angular/common/http';
import { PacienteService } from '../../services/paciente.service';
import { SelectedPatientService } from '../../services/selected-patient.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-mensaje',
  standalone: false,
  templateUrl: './mensaje.component.html',
  styleUrls: ['./mensaje.component.css']
})
export class MensajeComponent implements OnInit {
  doctorName: string = 'Doctor';
  messages: any[] = [];
  newMessage: string = '';
  errorMessage: string = '';

  private baseUrl = 'http://localhost:8080'; // URL base de la API
  private urlchats = `${this.baseUrl}/chats`;
  private urlMandaMensaje = 'http://localhost:8080/mensajes';

  private mensajeSubject = new BehaviorSubject<any>(null);
  public mensaje$ = this.mensajeSubject.asObservable();

  public usertype = localStorage.getItem('userType'); // Tipo de usuario (paciente o médico)
  private idPaciente: any = null; // ID del paciente
  public chat: any = null; // Chat actual
  public chatId: any = null; // ID del chat actual
  public usertypeUpper: string = this.usertype ? this.usertype.toUpperCase() : '';
  constructor(
    private http: HttpClient,
    private pacienteService: PacienteService,
    private selectedPatientService: SelectedPatientService
  ) {}

  ngOnInit(): void {
    console.log('Tipo de usuario:', this.usertype);

    if (this.usertype === 'paciente') {
      // Cargar datos del paciente al inicializar el componente
      this.pacienteService.loadPaciente().subscribe({
        next: (paciente) => {
          this.idPaciente = paciente.id; // Obtiene el ID del paciente
          console.log('Paciente cargado:', this.idPaciente); // Debugging
          this.getChat();
        },
        error: (err: any) => {
          console.error('Error al cargar los datos del paciente:', err);
        }
      });
    } else if (this.usertype === 'medico') {
      this.selectedPatientService.getPatient().subscribe({
        next: (patient) => {
          console.log('Paciente seleccionado cargado:', patient);
          this.idPaciente = patient.id;
          this.getChat();
        },
        error: (err) => {
          console.error('Error al obtener paciente seleccionado:', err);
          this.mensajeSubject.next('Error al obtener paciente seleccionado');
        }
      });
    } else {
      console.error('Tipo de usuario no válido.');
      this.mensajeSubject.next('Tipo de usuario no válido.');
    }
  }

  // Obtener el chat relacionado con el paciente
  getChat(): void {
    if (this.idPaciente) {
      this.http.get<any[]>(`${this.urlchats}/paciente/${this.idPaciente}`).subscribe({
        next: (chats) => {
          if (chats && chats.length > 0) {
            this.chat = chats[0]; // Asigna el primer chat
            this.chatId = this.chat.chatId; // Obtiene el ID del chat
            console.log('Chat seleccionado:', this.chat);
            console.log('ID del chat cargado:', this.chatId);
            this.loadMessages(); // Cargar los mensajes
          } else {
            console.error('No se encontraron chats para el paciente.');
            this.mensajeSubject.next('No se encontraron chats para el paciente.');
          }
        },
        error: (err) => {
          console.error('Error al cargar los chats:', err);
          this.mensajeSubject.next('Error al cargar los chats');
        }
      });
    } else {
      console.error('No se pudo obtener el ID del paciente.');
      this.mensajeSubject.next('No se pudo obtener el ID del paciente');
    }
  }

  loadMessages(): void {
    this.messages = this.chat.mensajes;
  }

  sendMessage(): void {
    if (this.newMessage.trim() !== '') {
      const message = {
        chatId: this.chatId,
        contenido: this.newMessage,
        // tipo de usuario en mayusculas
        remitente: this.usertype ? this.usertype.toUpperCase() : '',
      };

      // Enviar mensaje
      this.http.post(this.urlMandaMensaje, message).subscribe({
        next: (response) => {
          console.log('Mensaje enviado:', response);
          this.newMessage = ''; // Limpiar campo de entrada
          this.getChat(); // Recargar chat
        },
        error: (err) => {
          console.error('Error al enviar mensaje:', err);
        }
      });
    }
  }
}
