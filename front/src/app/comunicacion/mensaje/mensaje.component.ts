import { Component, OnInit } from '@angular/core';
import { MensajesService } from '../../services/mensajes.service';

@Component({
  selector: 'app-mensaje',
  standalone: false,
  templateUrl: './mensaje.component.html',
  styleUrls: ['./mensaje.component.css']
})
export class MensajeComponent implements OnInit {
  doctorName: string = 'Dr. Smith';
  messages: any[] = [];
  newMessage: string = '';
  
  constructor(private mensajesService: MensajesService) {}

  ngOnInit(): void {
    this.mensajesService.mensaje$.subscribe((message) => {
      if (message) {
        this.messages.push(message);
      }
    });

    this.loadMessages();
  }

  loadMessages(): void {
    if (this.mensajesService.chatId) {
      this.mensajesService.getChatMessages(this.mensajesService.chatId).subscribe({
        next: (messages) => {
          this.messages = messages;
        },
        error: (err) => console.error('Error al cargar los mensajes:', err)
      });
    }
  }

  sendMessage(message: string): void {
    if (message.trim() !== '') {
      this.mensajesService.onSendMessage(message);
      this.newMessage = '';
    }
  }
}
