import { Component, OnInit } from '@angular/core';
import { SelectedAlertaService } from '../../services/selected-alerta.service';

@Component({
  selector: 'app-selected-alerta',
  standalone:false,
  templateUrl: './selected-alerta.component.html',
  styleUrls: ['./selected-alerta.component.css']
})
export class SelectedAlertaComponent implements OnInit {
  selectedAlerta: any | null = null;

  constructor(private selectedAlertaService: SelectedAlertaService) {}

  ngOnInit(): void {
    // Suscribirse al servicio para obtener la alerta seleccionada
    this.selectedAlertaService.getAlerta().subscribe(alerta => {
      this.selectedAlerta = alerta;
    });
  }
}
