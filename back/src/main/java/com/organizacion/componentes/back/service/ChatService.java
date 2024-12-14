package com.organizacion.componentes.back.service;

import com.organizacion.componentes.back.model.Chat;
import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.repository.ChatRepository;
import com.organizacion.componentes.back.repository.RepositoryMedico;
import com.organizacion.componentes.back.repository.RepositoryPaciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private RepositoryPaciente pacienteRepository;

    @Autowired
    private RepositoryMedico medicoRepository;

    // Método para crear chats automáticamente si no existen
    public void sincronizarChats() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        for (Paciente paciente : pacientes) {
            Medico medico = paciente.getMedico();
            if (medico != null) {
                // Verificar si ya existe un chat entre este paciente y médico
                boolean chatExiste = chatRepository.existsByPacienteIdAndMedicoId(paciente.getId(), medico.getId());
                if (!chatExiste) {
                    crearChat(paciente.getId(), medico.getId());
                }
            }
        }
    }

    // Método para verificar si existe un chat entre paciente y médico
    public boolean existeChatEntrePacienteYMedico(Long pacienteId, Long medicoId) {
        return chatRepository.existsByPacienteIdAndMedicoId(pacienteId, medicoId);
    }

    // Crear un nuevo chat
    public Chat crearChat(Long pacienteId, Long medicoId) {
        // Implementación actual que ya tienes
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Chat chat = new Chat(paciente, medico);
        return chatRepository.save(chat);
    }

    // Obtener todos los chats de un paciente
    public List<Chat> obtenerChatsPorPaciente(Long pacienteId) {
        return chatRepository.findByPacienteId(pacienteId);
    }

    public Chat obtenerChatPorId(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado con ID: " + chatId));
    }

    // Obtener todos los chats de un médico
    public List<Chat> obtenerChatsPorMedico(Long medicoId) {
        return chatRepository.findByMedicoId(medicoId);
    }
}
