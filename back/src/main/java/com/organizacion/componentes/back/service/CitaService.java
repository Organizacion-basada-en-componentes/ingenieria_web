package com.organizacion.componentes.back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    // Obtener todas las citas con paginación
    public Page<Cita> obtenerTodasLasCitas(Pageable pageable) {
        return citaRepository.findAll(pageable);
    }

    // Obtener una cita por ID
    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    // Crear una nueva cita
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    // Actualizar una cita existente
    public Optional<Cita> actualizarCita(Long id, Cita citaDetalles) {
        return citaRepository.findById(id)
                .map(cita -> {
                    cita.setFecha(citaDetalles.getFecha());
                    cita.setMedico(citaDetalles.getMedico());
                    cita.setPaciente(citaDetalles.getPaciente());
                    cita.setEstado(citaDetalles.getEstado());
                    return citaRepository.save(cita);
                });
    }

    // Eliminar una cita
    public boolean eliminarCita(Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
