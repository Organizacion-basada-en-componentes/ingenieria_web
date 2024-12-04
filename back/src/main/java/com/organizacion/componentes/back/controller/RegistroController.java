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

import com.organizacion.componentes.back.model.Registro;
import com.organizacion.componentes.back.service.RegistroService;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    // Obtener todos los registros
    @GetMapping
    public List<Registro> obtenerTodosLosRegistros() {
        return registroService.obtenerTodosLosRegistros();
    }

    // Obtener un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Registro> obtenerRegistroPorId(@PathVariable Long id) {
        Optional<Registro> registro = registroService.obtenerRegistroPorId(id);
        if (registro.isPresent()) {
            return ResponseEntity.ok(registro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo registro
    @PostMapping
    public Registro crearRegistro(@RequestBody Registro registro) {
        return registroService.crearRegistro(registro);
    }

    // Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<Registro> actualizarRegistro(@PathVariable Long id, @RequestBody Registro registroDetalles) {
        Optional<Registro> registroActualizado = registroService.actualizarRegistro(id, registroDetalles);
        if (registroActualizado.isPresent()) {
            return ResponseEntity.ok(registroActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un registro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        boolean eliminado = registroService.eliminarRegistro(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
