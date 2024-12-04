package com.organizacion.componentes.back.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacion.componentes.back.model.User;

public interface RepositoryUser extends JpaRepository<User, Long>{

}

