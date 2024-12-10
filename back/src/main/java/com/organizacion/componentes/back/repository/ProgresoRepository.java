package com.organizacion.componentes.back.repository;
import com.organizacion.componentes.back.model.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgresoRepository extends JpaRepository<Progreso, Long> {
}
