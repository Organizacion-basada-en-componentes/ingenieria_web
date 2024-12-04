package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;

public interface PlanDeRehabilitacionService {

    // Obtener todos los planes de rehabilitación
    List<PlanDeRehabilitacion> obtenerTodosLosPlanes();

    // Obtener un plan de rehabilitación por ID
    Optional<PlanDeRehabilitacion> obtenerPlanPorId(Long id);

    // Crear un nuevo plan de rehabilitación
    PlanDeRehabilitacion crearPlan(PlanDeRehabilitacion plan);

    // Actualizar un plan de rehabilitación existente
    Optional<PlanDeRehabilitacion> actualizarPlan(Long id, PlanDeRehabilitacion planDetalles);

    // Eliminar un plan de rehabilitación por ID
    boolean eliminarPlan(Long id);
}
