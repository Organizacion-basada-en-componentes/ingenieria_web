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

import com.organizacion.componentes.back.model.Ejercicio;
import com.organizacion.componentes.back.service.EjercicioService;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    // Obtener todos los ejercicios
    @GetMapping
    public List<Ejercicio> obtenerTodosLosEjercicios() {
        return ejercicioService.obtenerTodosLosEjercicios();
    }

    // Obtener un ejercicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> obtenerEjercicioPorId(@PathVariable Long id) {
        Optional<Ejercicio> ejercicio = ejercicioService.obtenerEjercicioPorId(id);
        if (ejercicio.isPresent()) {
            return ResponseEntity.ok(ejercicio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo ejercicio
    @PostMapping
    public Ejercicio crearEjercicio(@RequestBody Ejercicio ejercicio) {
        return ejercicioService.crearEjercicio(ejercicio);
    }

    // Actualizar un ejercicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> actualizarEjercicio(@PathVariable Long id, @RequestBody Ejercicio ejercicioDetalles) {
        Optional<Ejercicio> ejercicioActualizado = ejercicioService.actualizarEjercicio(id, ejercicioDetalles);
        if (ejercicioActualizado.isPresent()) {
            return ResponseEntity.ok(ejercicioActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un ejercicio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable Long id) {
        boolean eliminado = ejercicioService.eliminarEjercicio(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
