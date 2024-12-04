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

import com.organizacion.componentes.back.model.Videollamada;
import com.organizacion.componentes.back.service.VideollamadaService;

@RestController
@RequestMapping("/api/videollamadas")
public class VideollamadaController {

    @Autowired
    private VideollamadaService videollamadaService;

    // Obtener todas las videollamadas
    @GetMapping
    public List<Videollamada> obtenerTodasLasVideollamadas() {
        return videollamadaService.obtenerTodasLasVideollamadas();
    }

    // Obtener una videollamada por ID
    @GetMapping("/{id}")
    public ResponseEntity<Videollamada> obtenerVideollamadaPorId(@PathVariable Long id) {
        Optional<Videollamada> videollamada = videollamadaService.obtenerVideollamadaPorId(id);
        if (videollamada.isPresent()) {
            return ResponseEntity.ok(videollamada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva videollamada
    @PostMapping
    public Videollamada crearVideollamada(@RequestBody Videollamada videollamada) {
        return videollamadaService.crearVideollamada(videollamada);
    }

    // Actualizar una videollamada existente
    @PutMapping("/{id}")
    public ResponseEntity<Videollamada> actualizarVideollamada(@PathVariable Long id, @RequestBody Videollamada videollamadaDetalles) {
        Optional<Videollamada> videollamadaActualizada = videollamadaService.actualizarVideollamada(id, videollamadaDetalles);
        if (videollamadaActualizada.isPresent()) {
            return ResponseEntity.ok(videollamadaActualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una videollamada por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideollamada(@PathVariable Long id) {
        boolean eliminado = videollamadaService.eliminarVideollamada(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
