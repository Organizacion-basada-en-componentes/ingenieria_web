package com.organizacion.componentes.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Medico;

@Repository
public interface RepositoryMedico extends JpaRepository<Medico, Long> {  // Cambiado de Long a String

    Medico findByDni(String dni);  // Método para buscar médico por DNI
    Optional<Medico> findByUsuarioId(Long usuarioId); //obtener datos tabla medico por id
}