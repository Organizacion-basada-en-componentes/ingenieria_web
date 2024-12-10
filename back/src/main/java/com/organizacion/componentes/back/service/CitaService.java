package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.controller.citaRequest;
import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Cita getCitaById(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public Cita createCita(Cita cita) {
        // Solo guardar la cita directamente
        return citaRepository.save(cita);
    }

    public Cita updateCita(Long id, Cita cita) {
        if (citaRepository.existsById(id)) {
            cita.setId(id);
            return citaRepository.save(cita);
        }
        return null;
    }

    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }

    public List<Cita> getCitasByMedico(String id) {
        return citaRepository.findByMedico_Dni(id);
    }

    public List<Cita> getCitasByPaciente(String id) {
        return citaRepository.findByPaciente_Dni(id);
    }
}
