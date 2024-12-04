package com.organizacion.componentes.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;

@Repository
public interface PlanDeRehabilitacionRepository extends JpaRepository<PlanDeRehabilitacion, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}
