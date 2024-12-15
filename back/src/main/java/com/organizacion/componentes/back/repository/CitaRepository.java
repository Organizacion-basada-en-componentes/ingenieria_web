package com.organizacion.componentes.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.Cita;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Encontrar citas por DNI de médico
    List<Cita> findByMedico_Dni(String dni);

    // Encontrar citas por DNI de paciente
    List<Cita> findByPaciente_Dni(String dni);

    Cita findFirstByPacienteIdOrderByFechaHoraAsc(Long idPaciente);
}
