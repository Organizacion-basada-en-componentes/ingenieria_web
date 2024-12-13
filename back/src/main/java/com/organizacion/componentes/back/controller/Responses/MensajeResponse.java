package com.organizacion.componentes.back.controller.Responses;

import com.organizacion.componentes.back.model.Mensaje;
import com.organizacion.componentes.back.Enum.Remitente;

import java.time.LocalDateTime;

public class MensajeResponse {
    private Long id;
    private Long chatId;
    private Remitente remitente;
    private String contenido;
    private LocalDateTime fechaEnvio;

    public MensajeResponse(Mensaje mensaje) {
        this.id = mensaje.getId();
        this.chatId = mensaje.getChat().getId();
        this.remitente = mensaje.getRemitente();
        this.contenido = mensaje.getContenido();
        this.fechaEnvio = mensaje.getFechaEnvio();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public Remitente getRemitente() {
        return remitente;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }
}
