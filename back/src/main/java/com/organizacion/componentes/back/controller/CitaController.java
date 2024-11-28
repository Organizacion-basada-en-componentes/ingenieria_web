package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.model.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Obtener todas las citas
    @GetMapping
    public List<Cita> obtenerTodasLasCitas() {
        return citaService.obtenerTodasLasCitas();
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable Long id) {
        Optional<Cita> cita = citaService.obtenerCitaPorId(id);
        if (cita.isPresent()) {
            return ResponseEntity.ok(cita.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva cita
    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    // Actualizar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita citaDetalles) {
        Optional<Cita> citaActualizada = citaService.actualizarCita(id, citaDetalles);
        if (citaActualizada.isPresent()) {
            return ResponseEntity.ok(citaActualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        boolean eliminado = citaService.eliminarCita(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
