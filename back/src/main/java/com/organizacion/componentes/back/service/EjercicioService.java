package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Ejercicio;
import com.organizacion.componentes.back.repository.EjercicioRepository;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.repository.RepositoryPaciente;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private RepositoryPaciente pacienteRepository;

    // Obtener todos los ejercicios
    public List<Ejercicio> getAllEjercicios() {
        return ejercicioRepository.findAll();
    }

    // Obtener un ejercicio por su ID
    public Ejercicio getEjercicioById(Long id) {
        Optional<Ejercicio> ejercicio = ejercicioRepository.findById(id);
        if (ejercicio.isPresent()) {
            return ejercicio.get();
        } else {
            throw new RuntimeException("Ejercicio no encontrado con ID: " + id);
        }
    }

    // Crear un nuevo ejercicio
    public Ejercicio createEjercicio(Ejercicio ejercicio) {
        // Verificar si el paciente existe
        Optional<Paciente> paciente = pacienteRepository.findById(ejercicio.getPaciente().getDni());
        if (paciente.isPresent()) {
            ejercicio.setPaciente(paciente.get());  // Asociar el paciente al ejercicio
            return ejercicioRepository.save(ejercicio);
        } else {
            throw new RuntimeException("Paciente no encontrado con DNI: " + ejercicio.getPaciente().getDni());
        }
    }

    // Actualizar un ejercicio existente
    public Ejercicio updateEjercicio(Long id, Ejercicio ejercicioActualizado) {
        Optional<Ejercicio> ejercicioExistente = ejercicioRepository.findById(id);
        if (ejercicioExistente.isPresent()) {
            Ejercicio ejercicio = ejercicioExistente.get();
            
            // Actualizar solo si los valores no son nulos
            if (ejercicioActualizado.getNombre() != null) {
                ejercicio.setNombre(ejercicioActualizado.getNombre());
            }

            // Actualizar solo si los valores no son nulos
            if (ejercicioActualizado.getDescripcion() != null) {
                ejercicio.setDescripcion(ejercicioActualizado.getDescripcion());
            }
            if (ejercicioActualizado.getRepeticiones() > 0) {
                ejercicio.setRepeticiones(ejercicioActualizado.getRepeticiones());
            }
            if (ejercicioActualizado.getSeries() > 0) {
                ejercicio.setSeries(ejercicioActualizado.getSeries());
            }
            
            // Verificar si el paciente existe antes de asignarlo
            Optional<Paciente> paciente = pacienteRepository.findById(ejercicioActualizado.getPaciente().getDni());
            if (paciente.isPresent()) {
                ejercicio.setPaciente(paciente.get());
            } else {
                throw new RuntimeException("Paciente no encontrado con DNI: " + ejercicioActualizado.getPaciente().getDni());
            }
            
            return ejercicioRepository.save(ejercicio);
        } else {
            throw new RuntimeException("Ejercicio no encontrado con ID: " + id);
        }
    }

    // Eliminar un ejercicio por su ID
    public void deleteEjercicio(Long id) {
        if (ejercicioRepository.existsById(id)) {
            ejercicioRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Ejercicio no encontrado con ID: " + id);
        }
    }
}
