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

    

    // Eliminar un paciente por su DNI
    public void deletePaciente(String dni) {
        if (pacienteRepository.existsById(dni)) {
            pacienteRepository.deleteById(dni);
        } else {
            throw new RuntimeException("No se puede eliminar. Paciente no encontrado con DNI: " + dni);
        }
    }
}