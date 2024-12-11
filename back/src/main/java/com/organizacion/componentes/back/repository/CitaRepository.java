package com.organizacion.componentes.back.repository;

import com.organizacion.componentes.back.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPaciente_Dni(String dni);

    List<Cita> findByMedico_Dni(String dni);
}
