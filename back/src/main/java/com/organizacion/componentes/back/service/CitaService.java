package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import com.organizacion.componentes.back.model.Cita;

public interface CitaService {

    // Obtener todas las citas
    List<Cita> obtenerTodasLasCitas();

    // Obtener una cita por ID
    Optional<Cita> obtenerCitaPorId(Long id);

    // Crear una nueva cita
    Cita crearCita(Cita cita);

    // Actualizar una cita existente
    Optional<Cita> actualizarCita(Long id, Cita citaDetalles);

    // Eliminar una cita por ID
    boolean eliminarCita(Long id);
}
