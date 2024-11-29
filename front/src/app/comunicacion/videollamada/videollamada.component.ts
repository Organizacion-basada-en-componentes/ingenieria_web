import { Component } from '@angular/core';

@Component({
  selector: 'app-videollamada',
  standalone: false,
  
  templateUrl: './videollamada.component.html',
  styleUrl: './videollamada.component.css'
})
export class VideollamadaComponent {
  cameraOn = true;
  microphoneOn = true;

  toggleCamera() {
    this.cameraOn = !this.cameraOn;
    // Lógica para manejar la cámara (ej. habilitar/deshabilitar video)
  }

  toggleMicrophone() {
    this.microphoneOn = !this.microphoneOn;
    // Lógica para manejar el micrófono (ej. activar/desactivar audio)
  }

  endCall() {
    // Lógica para finalizar la llamada
    alert('Llamada finalizada');
  }
}