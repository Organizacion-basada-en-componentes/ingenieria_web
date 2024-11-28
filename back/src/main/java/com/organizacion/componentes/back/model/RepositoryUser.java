package com.organizacion.componentes.back.model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User, Long>{

	User findByUsername(String username);
}

