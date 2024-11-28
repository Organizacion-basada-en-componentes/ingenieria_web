package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.model.Mensaje;
import com.organizacion.componentes.back.model.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Endpoint para obtener todos los mensajes
    @GetMapping
    public List<Mensaje> getAllMensajes() {
        return mensajeService.getAllMensajes();
    }

    // Endpoint para obtener los mensajes de un paciente
    @GetMapping("/paciente/{pacienteId}")
    public List<Mensaje> getMensajesByPaciente(@PathVariable Long pacienteId) {
        return mensajeService.getMensajesByPaciente(pacienteId);
    }

    // Endpoint para obtener los mensajes de un médico
    @GetMapping("/medico/{medicoId}")
    public List<Mensaje> getMensajesByMedico(@PathVariable Long medicoId) {
        return mensajeService.getMensajesByMedico(medicoId);
    }

    // Endpoint para obtener un mensaje específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para guardar un mensaje
    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
        Mensaje savedMensaje = mensajeService.saveMensaje(mensaje);
        return new ResponseEntity<>(savedMensaje, HttpStatus.CREATED);
    }

    // Endpoint para eliminar un mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        if (mensaje.isPresent()) {
            mensajeService.deleteMensaje(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
