package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.repository.RepositoryPaciente;

@Service
public class PacienteService {

    @Autowired
    private RepositoryPaciente pacienteRepository;

    // Obtener todos los pacientes
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    // Obtener un paciente por su ID
    public Paciente getPacienteById(String dni) {
        Optional<Paciente> paciente = pacienteRepository.findById(dni);
        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            throw new RuntimeException("Paciente no encontrado con DNI: " + dni);
        }
    }

    // Crear un nuevo paciente
    public Paciente createPaciente(Paciente paciente) {
        if (paciente.getUsername() == null || paciente.getContraseña() == null) {
            throw new RuntimeException("El username y la contraseña no pueden ser nulos.");
        }
        return pacienteRepository.save(paciente);
    }

    // Actualizar un paciente existente
    public Paciente updatePaciente(String dni, Paciente pacienteActualizado) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(dni);
        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setEmail(pacienteActualizado.getEmail());
            paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
            // Actualizamos también el username y la contraseña si están presentes
            if (pacienteActualizado.getUsername() != null) {
                paciente.setUsername(pacienteActualizado.getUsername());
            }
            if (pacienteActualizado.getContraseña() != null) {
                paciente.setContraseña(pacienteActualizado.getContraseña());
            }
            return pacienteRepository.save(paciente);
        } else {
            throw new RuntimeException("No se puede actualizar. Paciente no encontrado con DNI: " + dni);
        }
    }

    // Eliminar un paciente por su DNI
    public void deletePaciente(String dni) {
        if (pacienteRepository.existsById(dni)) {
            pacienteRepository.deleteById(dni);
        } else {
            throw new RuntimeException("No se puede eliminar. Paciente no encontrado con DNI: " + dni);
        }
    }
}