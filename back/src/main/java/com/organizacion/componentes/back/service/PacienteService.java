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
    private RepositoryPaciente repositoryPaciente;

    // Obtener todos los pacientes
    public List<Paciente> obtenerTodosLosPacientes() {
        return repositoryPaciente.findAll();
    }

    // Obtener un paciente por ID
    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return repositoryPaciente.findById(id);
    }

    // Crear un nuevo paciente
    public Paciente crearPaciente(Paciente paciente) {
        return repositoryPaciente.save(paciente);
    }

    // Actualizar un paciente existente
    public Optional<Paciente> actualizarPaciente(Long id, Paciente pacienteDetalles) {
        return repositoryPaciente.findById(id).map(paciente -> {
            paciente.setDni(pacienteDetalles.getDni());
            paciente.setNombre(pacienteDetalles.getNombre());
            paciente.setCitas(pacienteDetalles.getCitas());
            return repositoryPaciente.save(paciente);
        });
    }

    // Eliminar un paciente por ID
    public boolean eliminarPaciente(Long id) {
        if (repositoryPaciente.existsById(id)) {
            repositoryPaciente.deleteById(id);
            return true;
        }
        return false;
    }
}
