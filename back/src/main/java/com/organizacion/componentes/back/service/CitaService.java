package com.organizacion.componentes.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public Cita createCita(Cita cita) {
        // Guardar la cita directamente
        return citaRepository.save(cita);
    }

    public Cita getCitaById(Long idCita) {
        return citaRepository.findById(idCita).orElse(null);
    }

    public Cita getProximaCitaByPacienteId(Long idPaciente) {
        return citaRepository.findFirstByPacienteIdOrderByFechaHoraAsc(idPaciente);
    }

    // Eliminar una cita por su ID
    public boolean deleteCitaById(Long idCita) {
        if (citaRepository.existsById(idCita)) {
            citaRepository.deleteById(idCita);
            return true;
        }
        return false;
    }
}
