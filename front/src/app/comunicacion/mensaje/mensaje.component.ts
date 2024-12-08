import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mensaje',
  standalone: false,
  templateUrl: './mensaje.component.html',
  styleUrls: ['./mensaje.component.css'],
})
export class MensajeComponent implements OnInit {
  messages: { content: string; sender: string; timestamp: Date }[] = [];
  doctorName: string | null = null;
  newMessage: string = '';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const chatId = this.route.snapshot.paramMap.get('id');
    if (chatId) {
      this.loadChat(Number(chatId));
    }
  }

  loadChat(chatId: number): void {
    // Simulación de datos de chats
    const chats: Record<number, {
      doctorName: string;
      messages: { content: string; sender: string; timestamp: Date }[];
    }> = {
      1: {
        doctorName: 'Dr. García',
        messages: [
          { content: 'Hola, ¿cómo estás?', sender: 'medico', timestamp: new Date() },
          { content: 'Estoy bien, gracias por preguntar.', sender: 'paciente', timestamp: new Date() },
        ],
      },
      2: {
        doctorName: 'Dra. Martínez',
        messages: [
          { content: '¿Cómo sigues con los ejercicios?', sender: 'medico', timestamp: new Date() },
          { content: 'Muy bien, gracias.', sender: 'paciente', timestamp: new Date() },
        ],
      },
    };

    const chat = chats[chatId];
    if (chat) {
      this.doctorName = chat.doctorName;
      this.messages = chat.messages;
    }
  }

  sendMessage(message: string): void {
    if (message.trim()) {
      this.messages.push({
        content: message,
        sender: 'paciente',  // Suponiendo que el mensaje es del paciente
        timestamp: new Date(),
      });
      this.newMessage = '';  // Limpiar el campo de entrada después de enviar el mensaje
    }
  }
}
