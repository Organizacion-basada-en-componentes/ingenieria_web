package com.organizacion.componentes.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByChatIdOrderByFechaEnvioAsc(Long chatId);
}
