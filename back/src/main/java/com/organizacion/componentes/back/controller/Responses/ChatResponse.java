package com.organizacion.componentes.back.controller.Responses;

import java.util.List;
import java.util.stream.Collectors;

import com.organizacion.componentes.back.controller.Responses.MensajeResponse;
import com.organizacion.componentes.back.model.Chat;

public class ChatResponse {
    private Long chatId;
    private Long pacienteId;
    private Long medicoId;
    private List<MensajeResponse> mensajes;

    public ChatResponse(Chat chat) {
        this.chatId = chat.getId();
        this.pacienteId = chat.getPaciente().getId();
        this.medicoId = chat.getMedico().getId();
        this.mensajes = chat.getMensajes().stream()
                .map(MensajeResponse::new)
                .collect(Collectors.toList());
    }

    // Getters
    public Long getChatId() {
        return chatId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public List<MensajeResponse> getMensajes() {
        return mensajes;
    }
}
