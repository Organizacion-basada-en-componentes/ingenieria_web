package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;
import com.organizacion.componentes.back.service.PlanDeRehabilitacionService;

@RestController
@RequestMapping("/planes")
public class PlanDeRehabilitacionController {

    @Autowired
    private PlanDeRehabilitacionService planService;

    @PostMapping("/{idPaciente}")
    public ResponseEntity<?> crearOEditarPlan(@RequestBody PlanDeRehabilitacion plan, @PathVariable Long idPaciente) {
        if (plan.getId() != null && planService.existePlanPorId(plan.getId())) {
            PlanDeRehabilitacion planExistente = planService.obtenerPlanPorId(plan.getId());
            
            // Actualizar los ejercicios existentes o añadir nuevos
            planExistente.getEjercicios().clear();
            planExistente.getEjercicios().addAll(plan.getEjercicios());
            
            // Actualizar otros atributos del plan
            planExistente.setNombre(plan.getNombre());
            planExistente.setIdPaciente(idPaciente);
            
            // Guardar el plan actualizado
            PlanDeRehabilitacion planActualizado = planService.actualizarPlan(planExistente);
            return ResponseEntity.ok(planActualizado);
        } else {
            plan.setIdPaciente(idPaciente);
            PlanDeRehabilitacion nuevoPlan = planService.crearPlan(plan);
            return ResponseEntity.ok(nuevoPlan);
        }
    }

    @GetMapping
    public List<PlanDeRehabilitacion> obtenerTodos() {
        return planService.obtenerTodos();
    }

    @GetMapping("/paciente/{idPaciente}")
    public List<PlanDeRehabilitacion> obtenerPlanPorIdPaciente(@PathVariable Long idPaciente) {
        return planService.obtenerPlanPorIdPaciente(idPaciente);
    }
}
