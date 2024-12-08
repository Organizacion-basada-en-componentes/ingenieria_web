import { Component } from '@angular/core';

@Component({
  selector: 'app-lobby',
  standalone: false,
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css'],
})
export class LobbyComponent {
  // Simulación de datos
  chats = [
    { id: 1, doctorName: 'Dr. García', lastMessage: 'Recuerda tomar tus medicamentos.', timestamp: new Date() },
    { id: 2, doctorName: 'Dra. Martínez', lastMessage: '¿Cómo sigues con los ejercicios?', timestamp: new Date() },
  ];
}
