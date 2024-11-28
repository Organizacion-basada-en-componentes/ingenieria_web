package com.organizacion.componentes.back.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Método para obtener los mensajes por paciente
    List<Mensaje> findByPacienteId(Long pacienteId);

    // Método para obtener los mensajes por médico
    List<Mensaje> findByMedicoId(Long medicoId);
}
