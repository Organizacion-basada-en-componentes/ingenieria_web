package com.organizacion.componentes.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.organizacion.componentes.back.model.Medico;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMedico extends JpaRepository<Medico, String> {  // Cambiado de Long a String

    Medico findByDni(String dni);  // Método para buscar médico por DNI
}
