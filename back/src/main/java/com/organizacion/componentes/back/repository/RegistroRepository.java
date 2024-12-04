package com.organizacion.componentes.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}
