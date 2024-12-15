import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { PacienteService } from './paciente.service';
import { SelectedPatientService } from './selected-patient.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MensajesService implements OnInit {
  private baseUrl = 'http://localhost:8080'; // URL base de la API
  private urlchats = `${this.baseUrl}/chats`;

  private mensajeSubject = new BehaviorSubject<any>(null);
  public mensaje$ = this.mensajeSubject.asObservable();

  private usertype = localStorage.getItem('usertype'); // Tipo de usuario (paciente o médico)
  private idPaciente: any = null; // ID del paciente
  public chat: any = null; // Chat actual
  public chatId: any = null; // ID del chat actual

  constructor(
    private http: HttpClient,
    private pacienteService: PacienteService,
    private selectedPatientService: SelectedPatientService
  ) {}

  ngOnInit(): void {
    console.log('Tipo de usuario:', this.usertype);

    // Cargar los datos dependiendo del tipo de usuario
    if (this.usertype === 'paciente') {
      // Cargar datos del paciente al inicializar el componente
      this.pacienteService.loadPaciente().subscribe({
        next: (paciente) => {
          this.idPaciente = paciente.id; // Obtiene el ID del paciente
          console.log('Paciente cargado:', this.idPaciente); // Debugging
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

  // Obtener los mensajes para un chat
  getChatMessages(chatId: string) {
    console.log('Obteniendo mensajes para el chat ID:', chatId);
    return this.http.get<any[]>(`${this.urlchats}/${chatId}/mensajes`);
  }

  // Enviar un mensaje en el chat
  onSendMessage(message: string): void {
    if (!this.chatId) {
      console.error('No se pudo obtener el ID del chat para enviar el mensaje.');
      this.mensajeSubject.next('Error: No se pudo obtener el ID del chat');
      return;
    }

    this.http.post<any>(`${this.baseUrl}/mensajes`, {
      chatId: this.chatId,
      contenido: message,
      remitente: this.usertype
    }).subscribe({
      next: (response) => {
        console.log('Mensaje enviado:', response);
        this.mensajeSubject.next(response);
      },
      error: (err) => {
        console.error('Error al enviar el mensaje:', err);
        this.mensajeSubject.next(`Error al enviar el mensaje: ${err}`);
      }
    });
  }
}
