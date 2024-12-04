package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.repository.CitaRepository;

@Service
public class CitaService {

    // Inyección de dependencias para el repositorio de Cita
    @Autowired
    private CitaRepository citaRepository;

    // Obtener todas las citas
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll(); // Retorna todas las citas de la base de datos
    }

    // Obtener una cita por ID
    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id); // Busca una cita por su ID
    }

    // Crear una nueva cita
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita); // Guarda la nueva cita en la base de datos
    }

    // Actualizar una cita existente
    public Optional<Cita> actualizarCita(Long id, Cita citaDetalles) {
        // Verificar si la cita existe
        Optional<Cita> citaExistente = citaRepository.findById(id);
        if (citaExistente.isPresent()) {
            // Si la cita existe, se actualizan los detalles
            Cita cita = citaExistente.get();
            cita.setFecha(citaDetalles.getFecha());
            cita.setPaciente(citaDetalles.getPaciente());
            cita.setMedico(citaDetalles.getMedico());
            // Actualiza la cita en la base de datos
            return Optional.of(citaRepository.save(cita));
        }
        return Optional.empty(); // Si no existe la cita, retornamos un Optional vacío
    }

    // Eliminar una cita por ID
    public boolean eliminarCita(Long id) {
        // Verifica si la cita existe antes de eliminarla
        Optional<Cita> citaExistente = citaRepository.findById(id);
        if (citaExistente.isPresent()) {
            citaRepository.deleteById(id); // Elimina la cita por su ID
            return true; // Retorna true si la cita fue eliminada exitosamente
        }
        return false; // Retorna false si la cita no existe
    }
}
