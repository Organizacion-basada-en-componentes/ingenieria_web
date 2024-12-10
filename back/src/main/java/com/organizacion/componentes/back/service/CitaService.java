package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Cita getCitaById(Long id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.orElse(null);
    }

    public Cita createCita(Cita cita) {
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

    // Obtener todas las citas de un médico por su DNI
    public List<Cita> getCitasByMedico(String dni) {
        return citaRepository.findByMedico_Dni(dni);
    }

    // Obtener todas las citas de un paciente por su DNI
    public List<Cita> getCitasByPaciente(String dni) {
        return citaRepository.findByPaciente_Dni(dni);
    }
}
