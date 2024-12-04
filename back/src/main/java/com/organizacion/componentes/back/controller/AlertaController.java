package com.organizacion.componentes.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Alerta;
import com.organizacion.componentes.back.service.AlertaService;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    // Obtener todas las alertas
    @GetMapping
    public List<Alerta> obtenerTodasLasAlertas() {
        return alertaService.obtenerTodasLasAlertas();
    }

    // Obtener una alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alerta> obtenerAlertaPorId(@PathVariable Long id) {
        Optional<Alerta> alerta = alertaService.obtenerAlertaPorId(id);
        if (alerta.isPresent()) {
            return ResponseEntity.ok(alerta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva alerta
    @PostMapping
    public Alerta crearAlerta(@RequestBody Alerta alerta) {
        return alertaService.crearAlerta(alerta);
    }

    // Actualizar una alerta existente
    @PutMapping("/{id}")
    public ResponseEntity<Alerta> actualizarAlerta(@PathVariable Long id, @RequestBody Alerta alertaDetalles) {
        Optional<Alerta> alertaActualizada = alertaService.actualizarAlerta(id, alertaDetalles);
        if (alertaActualizada.isPresent()) {
            return ResponseEntity.ok(alertaActualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una alerta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlerta(@PathVariable Long id) {
        boolean eliminado = alertaService.eliminarAlerta(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
