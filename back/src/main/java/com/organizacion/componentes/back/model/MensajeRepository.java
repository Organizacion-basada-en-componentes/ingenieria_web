package com.organizacion.componentes.back.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Método para obtener los mensajes por paciente
    List<Mensaje> findByPacienteId(Long pacienteId);

    // Método para obtener los mensajes por médico
    List<Mensaje> findByMedicoId(Long medicoId);
}
