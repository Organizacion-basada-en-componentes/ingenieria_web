package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;
import com.organizacion.componentes.back.repository.PlanDeRehabilitacionRepository;

@Service
public class PlanDeRehabilitacionService {

    private final PlanDeRehabilitacionRepository planDeRehabilitacionRepository;

    // Inyección de dependencias en el constructor
    
    public PlanDeRehabilitacionService(PlanDeRehabilitacionRepository planDeRehabilitacionRepository) {
        this.planDeRehabilitacionRepository = planDeRehabilitacionRepository;
    }

    // Obtener todos los planes de rehabilitación
    public List<PlanDeRehabilitacion> obtenerTodosLosPlanes() {
        return planDeRehabilitacionRepository.findAll();
    }

    // Obtener un plan de rehabilitación por ID
    public Optional<PlanDeRehabilitacion> obtenerPlanPorId(Long id) {
        return planDeRehabilitacionRepository.findById(id);
    }

    // Crear un nuevo plan de rehabilitación
    public PlanDeRehabilitacion crearPlan(PlanDeRehabilitacion plan) {
        return planDeRehabilitacionRepository.save(plan);
    }

    // Actualizar un plan de rehabilitación existente
    public Optional<PlanDeRehabilitacion> actualizarPlan(Long id, PlanDeRehabilitacion planDetalles) {
        Optional<PlanDeRehabilitacion> planExistente = planDeRehabilitacionRepository.findById(id);
        if (planExistente.isPresent()) {
            PlanDeRehabilitacion plan = planExistente.get();
            plan.setNombre(planDetalles.getNombre());
            // Actualiza otros campos según sea necesario
            return Optional.of(planDeRehabilitacionRepository.save(plan));
        }
        return Optional.empty();
    }

    // Eliminar un plan de rehabilitación por ID
    public boolean eliminarPlan(Long id) {
        Optional<PlanDeRehabilitacion> plan = planDeRehabilitacionRepository.findById(id);
        if (plan.isPresent()) {
            planDeRehabilitacionRepository.delete(plan.get());
            return true;
        }
        return false;
    }
}
