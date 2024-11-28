package com.organizacion.componentes.back.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}
