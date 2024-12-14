package com.organizacion.componentes.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PlanDeRehabilitacion actualizarPlan(PlanDeRehabilitacion plan) {
        return planRepository.save(plan);
    }
}
