package com.organizacion.componentes.back.repository;

import com.organizacion.componentes.back.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositoryPaciente extends JpaRepository<Paciente, String> {
    Optional<Paciente> findByDni(String dni); // Este método es necesario

    Optional<Paciente> findById(String dni);

    boolean existsById(String dni);

    void deleteById(String dni);
}
