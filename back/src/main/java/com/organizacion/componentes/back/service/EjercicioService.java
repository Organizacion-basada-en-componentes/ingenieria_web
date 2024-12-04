package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Ejercicio;
import com.organizacion.componentes.back.repository.EjercicioRepository;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    // Obtener todos los ejercicios
    public List<Ejercicio> obtenerTodosLosEjercicios() {
        return ejercicioRepository.findAll();
    }

    // Obtener un ejercicio por ID
    public Optional<Ejercicio> obtenerEjercicioPorId(Long id) {
        return ejercicioRepository.findById(id);
    }

    // Crear un nuevo ejercicio
    public Ejercicio crearEjercicio(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    // Actualizar un ejercicio existente
    public Optional<Ejercicio> actualizarEjercicio(Long id, Ejercicio ejercicioDetalles) {
        if (ejercicioRepository.existsById(id)) {
            ejercicioDetalles.setId(id);
            return Optional.of(ejercicioRepository.save(ejercicioDetalles));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar un ejercicio por ID
    public boolean eliminarEjercicio(Long id) {
        if (ejercicioRepository.existsById(id)) {
            ejercicioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
