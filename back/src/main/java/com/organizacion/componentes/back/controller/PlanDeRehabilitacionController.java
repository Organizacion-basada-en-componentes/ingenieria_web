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
    public ResponseEntity<PlanDeRehabilitacion> crearOEditarPlan(
            @PathVariable Long idPaciente, 
            @RequestBody PlanDeRehabilitacion plan) {

        // Debugging: Verifica que el plan y el idPaciente son correctos
        System.out.println("Recibido plan: " + plan);
        System.out.println("ID del paciente: " + idPaciente);
        
        // Verifica si el plan tiene ID y si ya existe en la base de datos
        if (plan.getId() != null && planService.existePlanPorId(plan.getId())) {
            System.out.println("Plan existente encontrado con ID: " + plan.getId());

            // Si existe, actualiza el plan
            PlanDeRehabilitacion planExistente = planService.obtenerPlanPorId(plan.getId());

            // Actualiza los atributos necesarios
            planExistente.setNombre(plan.getNombre());
            planExistente.setEjercicios(plan.getEjercicios());
            planExistente.setIdPaciente(idPaciente);

            // Guarda el plan actualizado
            PlanDeRehabilitacion planActualizado = planService.actualizarPlan(planExistente);
            return ResponseEntity.ok(planActualizado);
        } else {
            System.out.println("Creando nuevo plan para paciente ID: " + idPaciente);
            // Si no existe, crea un nuevo plan
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
