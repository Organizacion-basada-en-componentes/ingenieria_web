package com.organizacion.componentes.back.repository;

import com.organizacion.componentes.back.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositoryMedico extends JpaRepository<Medico, String> {
    Optional<Medico> findByDni(String dni); // Este método es necesario

    void deleteById(String dni);
}
