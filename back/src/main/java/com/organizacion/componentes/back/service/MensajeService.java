package com.organizacion.componentes.back.service;

import com.organizacion.componentes.back.controller.requests.MensajeRequest;
import com.organizacion.componentes.back.model.Chat;
import com.organizacion.componentes.back.model.Mensaje;
import com.organizacion.componentes.back.Enum.Remitente;
import com.organizacion.componentes.back.repository.ChatRepository;
import com.organizacion.componentes.back.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ChatRepository chatRepository;

    // Crear un mensaje
    public Mensaje crearMensaje(MensajeRequest request) {
        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));

        Remitente remitente = Remitente.valueOf(request.getRemitente().toUpperCase());
        Mensaje mensaje = new Mensaje(chat, remitente, request.getContenido(), LocalDateTime.now());
        return mensajeRepository.save(mensaje);
    }

    // Obtener todos los mensajes de un chat
    public List<Mensaje> obtenerMensajesDeChatOrdenados(Long chatId) {
        return mensajeRepository.findByChatIdOrderByFechaEnvioAsc(chatId);
    }

}
