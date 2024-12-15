package com.organizacion.componentes.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.organizacion.componentes.back.model.Ejercicio;
import com.organizacion.componentes.back.model.PlanDeRehabilitacion;
import com.organizacion.componentes.back.repository.PlanDeRehabilitacionRepository;

@Service
public class PlanDeRehabilitacionService {

    @Autowired
    private PlanDeRehabilitacionRepository planRepository;

    public PlanDeRehabilitacion crearPlan(PlanDeRehabilitacion plan) {
        // Asegúrate de que el plan tenga el idPaciente correctamente establecido
        return planRepository.save(plan);
    }

    public List<PlanDeRehabilitacion> obtenerTodos() {
        return planRepository.findAll();
    }

    public PlanDeRehabilitacion obtenerPlanPorId(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new RuntimeException("Plan no encontrado"));
    }

    @Transactional
    public PlanDeRehabilitacion actualizarPlan(PlanDeRehabilitacion plan) {
        PlanDeRehabilitacion planExistente = planRepository.findById(plan.getId())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        // Actualizar campos
        planExistente.setNombre(plan.getNombre());
        planExistente.setIdPaciente(plan.getIdPaciente());

        // Asegúrate de que los ejercicios estén correctamente vinculados
        for (Ejercicio ejercicio : plan.getEjercicios()) {
            ejercicio.setPlanDeRehabilitacion(planExistente);
        }

        // Guardar el plan
        return planRepository.save(planExistente);
    }

    public List<PlanDeRehabilitacion> obtenerPlanPorIdPaciente(Long idPaciente) {
        return planRepository.findByIdPaciente(idPaciente);
    }
    public boolean existePlanPorId(Long id) {
        return planRepository.existsById(id);
    }
    
}
