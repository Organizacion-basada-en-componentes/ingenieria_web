package com.organizacion.componentes.back.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;

public interface PlanDeRehabilitacionRepository extends JpaRepository<PlanDeRehabilitacion, Long> {
    @Override
    List<PlanDeRehabilitacion> findAll();
    List<PlanDeRehabilitacion> findByIdPaciente(Long idPaciente);
}
