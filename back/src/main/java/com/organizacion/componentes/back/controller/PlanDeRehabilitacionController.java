package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<PlanDeRehabilitacion> crearPlan(
            @PathVariable Long idPaciente, 
            @RequestBody PlanDeRehabilitacion plan) {
        
        // Establecer el idPaciente en el plan
        plan.setIdPaciente(idPaciente);
        
        // Crear el plan y asociarlo con el paciente
        PlanDeRehabilitacion nuevoPlan = planService.crearPlan(plan);
        return ResponseEntity.ok(nuevoPlan);
    }

    @GetMapping
    public List<PlanDeRehabilitacion> obtenerTodos() {
        return planService.obtenerTodos();
    }

    // Endpoint para modificar un plan de rehabilitación
    @PutMapping("/{id}")
    public ResponseEntity<PlanDeRehabilitacion> modificarPlan(
            @PathVariable Long id,
            @RequestBody PlanDeRehabilitacion planActualizado) {
        
        // Primero, obtienes el plan existente por su ID
        PlanDeRehabilitacion planExistente = planService.obtenerPlanPorId(id);

        // Actualizas los atributos del plan con los datos del cuerpo de la solicitud
        planExistente.setNombre(planActualizado.getNombre());
        planExistente.setEjercicios(planActualizado.getEjercicios());
        planExistente.setIdPaciente(planActualizado.getIdPaciente());  // Si es necesario

        // Guardas el plan actualizado
        PlanDeRehabilitacion planGuardado = planService.actualizarPlan(planExistente);

        return ResponseEntity.ok(planGuardado);
    }
}
