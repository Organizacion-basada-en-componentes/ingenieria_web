package com.organizacion.componentes.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.Paciente;


public interface RepositoryPaciente extends JpaRepository<Paciente, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario, por ejemplo:
    // Optional<Paciente> findByNombre(String nombre);
    Optional<Paciente> findByUsuarioId(Long usuarioId);

}