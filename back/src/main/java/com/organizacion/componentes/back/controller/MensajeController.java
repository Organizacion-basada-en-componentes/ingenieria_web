package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.controller.Responses.MensajeResponse;
import com.organizacion.componentes.back.controller.requests.MensajeRequest;
import com.organizacion.componentes.back.model.Mensaje;
import com.organizacion.componentes.back.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Crear un nuevo mensaje
    @PostMapping
    public ResponseEntity<MensajeResponse> crearMensaje(@RequestBody MensajeRequest request) {
        Mensaje mensaje = mensajeService.crearMensaje(request);
        MensajeResponse response = new MensajeResponse(mensaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener todos los mensajes de un chat
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MensajeResponse>> obtenerMensajesDeChat(@PathVariable Long chatId) {
        List<Mensaje> mensajes = mensajeService.obtenerMensajesDeChatOrdenados(chatId);
        List<MensajeResponse> response = mensajes.stream()
                .map(MensajeResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
