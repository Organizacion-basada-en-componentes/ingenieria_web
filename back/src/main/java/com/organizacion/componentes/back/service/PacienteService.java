package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.repository.RepositoryMedico;
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
    public Paciente getPacienteById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
    }

    // Eliminar un paciente por su DNI
    public void deletePaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Paciente no encontrado con ID: " + id);
        }
    }

    @Autowired
    private RepositoryMedico medicoRepository;

    // Si queremos crear los chats de los pacientes automaticamente
    // @Autowired
    // private ChatService chatService;

    // Método para crear un nuevo paciente y asignar un médico automáticamente
    public Paciente crearPaciente(Paciente nuevoPaciente) {
        // Buscar un médico disponible (ejemplo: el médico con menos pacientes)
        List<Medico> medicos = medicoRepository.findAll();

        if (medicos.isEmpty()) {
            throw new RuntimeException("No hay médicos disponibles para asignar al paciente.");
        }

        // Encuentra el médico con menos pacientes
        Medico medicoDisponible = medicos.stream()
                .min((m1, m2) -> Integer.compare(m1.getPacientes().size(), m2.getPacientes().size()))
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar un médico disponible."));

        // Asignar el médico al paciente
        nuevoPaciente.setMedico(medicoDisponible);

        // Añadir el paciente a la lista de pacientes del médico
        medicoDisponible.addPaciente(nuevoPaciente);

        // Guardar el paciente y el médico
        pacienteRepository.save(nuevoPaciente);
        medicoRepository.save(medicoDisponible);

        // chatService.crearChat(nuevoPaciente.getId(), medicoDisponible.getId()); //
        // solucion fuerza bruta
        return nuevoPaciente;
    }
}