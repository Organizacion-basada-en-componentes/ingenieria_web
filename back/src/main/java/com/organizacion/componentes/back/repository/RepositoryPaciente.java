package com.organizacion.componentes.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizacion.componentes.back.model.Paciente;

@Repository
public interface RepositoryPaciente extends JpaRepository<Paciente, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario, por ejemplo:
    // Optional<Paciente> findByNombre(String nombre);
}
