package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.controller.Responses.ChatResponse;
import com.organizacion.componentes.back.controller.requests.ChatRequest;
import com.organizacion.componentes.back.model.Chat;
import com.organizacion.componentes.back.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // Obtener un chat por ID
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponse> obtenerChatPorId(@PathVariable Long chatId) {
        Chat chat = chatService.obtenerChatPorId(chatId);
        if (chat != null) {
            ChatResponse response = new ChatResponse(chat);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo chat
    @PostMapping
    public ResponseEntity<ChatResponse> crearChat(@RequestBody ChatRequest request) {
        Chat chat = chatService.crearChat(request.getPacienteId(), request.getMedicoId());
        ChatResponse response = new ChatResponse(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Sincronizar chats automáticamente
    @PostMapping("/sincronizar")
    public ResponseEntity<String> sincronizarChats() {
        chatService.sincronizarChats();
        return ResponseEntity.ok("Sincronización de chats completada.");
    }

    // Obtener todos los chats de un paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ChatResponse>> obtenerChatsDePaciente(@PathVariable Long pacienteId) {
        List<Chat> chats = chatService.obtenerChatsPorPaciente(pacienteId);
        if (!chats.isEmpty()) {
            List<ChatResponse> responses = chats.stream().map(ChatResponse::new).collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obtener todos los chats de un médico
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ChatResponse>> obtenerChatsDeMedico(@PathVariable Long medicoId) {
        List<Chat> chats = chatService.obtenerChatsPorMedico(medicoId);
        if (!chats.isEmpty()) {
            List<ChatResponse> responses = chats.stream().map(ChatResponse::new).collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
