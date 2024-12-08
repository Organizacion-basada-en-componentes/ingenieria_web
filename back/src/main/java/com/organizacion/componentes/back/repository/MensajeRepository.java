package com.organizacion.componentes.back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Este método ya está disponible en JpaRepository y permite obtener todos los
    // mensajes con paginación
    @SuppressWarnings("null")
    Page<Mensaje> findAll(Pageable pageable);

}
