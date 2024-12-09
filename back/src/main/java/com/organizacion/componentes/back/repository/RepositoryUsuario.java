package com.organizacion.componentes.back.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.Usuario;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);

}

