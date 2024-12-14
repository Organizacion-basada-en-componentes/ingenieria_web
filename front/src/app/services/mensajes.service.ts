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

    if (this.usertype === 'paciente') {
      this.pacienteService.loadPaciente().subscribe({
        next: (paciente) => {
          console.log('Paciente cargado:', paciente);
          this.idPaciente = paciente.id;
          this.getChat();
        },
        error: (err) => console.error('Error al cargar datos del paciente:', err)
      });
    } else if (this.usertype === 'medico') {
      this.selectedPatientService.getPatient().subscribe({
        next: (patient) => {
          console.log('Paciente seleccionado cargado:', patient);
          this.idPaciente = patient.id;
          this.getChat();
        },
        error: (err) => console.error('Error al obtener paciente seleccionado:', err)
      });
    } else {
      console.error('Tipo de usuario no válido.');
    }
  }

  getChat(): void {
    console.log('ID del paciente:', this.idPaciente);

    if (this.idPaciente) {
      this.http.get<any[]>(`${this.urlchats}/paciente/${this.idPaciente}`).subscribe({
        next: (chats) => {
          console.log('Chats recibidos:', chats);

          if (chats && chats.length > 0) {
            this.chat = chats[0]; // Asigna el primer chat
            this.chatId = this.chat.chatId; // Obtiene el ID del chat
            console.log('Chat seleccionado:', this.chat);
            console.log('ID del chat cargado:', this.chatId);
          } else {
            console.error('No se encontraron chats para el paciente.');
          }
        },
        error: (err) => console.error('Error al cargar los chats:', err)
      });
    } else {
      console.error('No se pudo obtener el ID del paciente.');
    }
  }

  getChatMessages(chatId: string) {
    console.log('Obteniendo mensajes para el chat ID:', chatId);
    return this.http.get<any[]>(`${this.urlchats}/${chatId}/mensajes`);
  }

  onSendMessage(message: string): void {
    console.log('Intentando enviar mensaje:', message);
    console.log('ID del chat actual:', this.chatId);

    if (this.chatId) {
      this.http.post<any>(`${this.baseUrl}/mensajes`, {
        chatId: this.chatId,
        contenido: message,
        remitente: this.usertype
      }).subscribe({
        next: (response) => console.log('Mensaje enviado:', response),
        error: (err) => console.error('Error al enviar el mensaje:', err)
      });
    } else {
      console.error('No se pudo obtener el ID del chat para enviar el mensaje.');
      console.log('Chat actual:', this.chat); // Muestra el estado del chat para depuración
    }
  }
}
