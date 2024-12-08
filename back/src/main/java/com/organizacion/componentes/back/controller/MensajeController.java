package com.organizacion.componentes.back.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Mensaje;
import com.organizacion.componentes.back.service.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Endpoint para obtener todos los mensajes con paginación
    @GetMapping
    public ResponseEntity<Page<Mensaje>> getAllMensajes(Pageable pageable) {
        Page<Mensaje> mensajes = mensajeService.getAllMensajes(pageable);
        return ResponseEntity.ok(mensajes);
    }

    // Endpoint para obtener un mensaje específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para guardar un mensaje
    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody @Valid Mensaje mensaje) {
        Mensaje savedMensaje = mensajeService.saveMensaje(mensaje);
        return new ResponseEntity<>(savedMensaje, HttpStatus.CREATED);
    }

    // Endpoint para eliminar un mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMensaje(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        if (mensaje.isPresent()) {
            mensajeService.deleteMensaje(id);
            return ResponseEntity.ok("Mensaje eliminado correctamente");
        }
        return ResponseEntity.notFound().build();
    }
}
