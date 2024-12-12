package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;
import com.organizacion.componentes.back.repository.PlanDeRehabilitacionRepository;

@Service
public class PlanDeRehabilitacionService {

    @Autowired
    private PlanDeRehabilitacionRepository planRepository;

    public PlanDeRehabilitacion crearPlan(PlanDeRehabilitacion plan) {
        return planRepository.save(plan);
    }

    public List<PlanDeRehabilitacion> obtenerTodos() {
        return planRepository.findAll();
    }
    public PlanDeRehabilitacion actualizarPlan(PlanDeRehabilitacion plan) {
        return planRepository.save(plan);
    }

   
    public PlanDeRehabilitacion obtenerPlanPorId(Long id) {

        Optional<PlanDeRehabilitacion> plan = planRepository.findById(id);

        if (plan.isPresent()) {

            return plan.get();

        } else {

            throw new  RuntimeException("Plan not found with id " + id);

        }

    }

}

    

