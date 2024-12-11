package com.organizacion.componentes.back.service;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.repository.CitaRepository;
import com.organizacion.componentes.back.repository.RepositoryMedico;
import com.organizacion.componentes.back.repository.RepositoryPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private RepositoryMedico medicoRepository;

    @Autowired
    private RepositoryPaciente pacienteRepository;

    public Cita crearCita(Cita cita) {
        // Validar si el médico existe con el DNI
        Optional<Medico> medicoOpt = medicoRepository.findByDni(cita.getMedicoDni());
        if (medicoOpt.isEmpty()) {
            throw new RuntimeException("El médico con DNI " + cita.getMedicoDni() + " no existe.");
        }

        // Validar si el paciente existe con el DNI
        Optional<Paciente> pacienteOpt = pacienteRepository.findByDni(cita.getPacienteDni());
        if (pacienteOpt.isEmpty()) {
            throw new RuntimeException("El paciente con DNI " + cita.getPacienteDni() + " no existe.");
        }

        // Asignar el médico y el paciente a la cita
        cita.setMedico(medicoOpt.get());
        cita.setPaciente(pacienteOpt.get());

        // Guardar la cita en la base de datos
        return citaRepository.save(cita);
    }
}
