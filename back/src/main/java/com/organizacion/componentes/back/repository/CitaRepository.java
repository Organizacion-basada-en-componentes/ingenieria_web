package com.organizacion.componentes.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Encontrar citas por DNI de médico
    List<Cita> findByMedico_Dni(String dni);

    // Encontrar citas por DNI de paciente
    List<Cita> findByPacienteId(Long idPaciete);

    Cita findFirstByPacienteIdOrderByFechaHoraAsc(Long idPaciente);
}
