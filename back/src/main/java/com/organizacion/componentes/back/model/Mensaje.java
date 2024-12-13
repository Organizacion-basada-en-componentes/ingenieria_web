package com.organizacion.componentes.back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.organizacion.componentes.back.Enum.Remitente;

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Enumerated(EnumType.STRING)
    private Remitente remitente;

    private String contenido;

    private LocalDateTime fechaEnvio;

    public Mensaje() {
    }

    public Mensaje(Chat chat, Remitente remitente, String contenido, LocalDateTime fechaEnvio) {
        this.chat = chat;
        this.remitente = remitente;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Remitente getRemitente() {
        return remitente;
    }

    public void setRemitente(Remitente remitente) {
        this.remitente = remitente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
