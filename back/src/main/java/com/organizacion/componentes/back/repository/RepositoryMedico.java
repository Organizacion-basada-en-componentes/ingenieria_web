package com.organizacion.componentes.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.Medico;

public interface RepositoryMedico extends JpaRepository<Medico, Long> {
    
}
