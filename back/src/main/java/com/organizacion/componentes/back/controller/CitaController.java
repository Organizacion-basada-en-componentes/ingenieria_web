package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        // Usamos los valores de medicoDni y pacienteDni dentro del cuerpo de la cita
        Cita nuevaCita = citaService.crearCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }
}
