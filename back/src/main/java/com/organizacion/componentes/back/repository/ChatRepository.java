package com.organizacion.componentes.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    // Puedes agregar métodos de consulta personalizados si es necesario, como
    // encontrar chats por paciente o médico
    boolean existsByPacienteIdAndMedicoId(Long pacienteId, Long medicoId);

    List<Chat> findByPacienteId(Long pacienteId);

    List<Chat> findByMedicoId(Long medicoId);

}
