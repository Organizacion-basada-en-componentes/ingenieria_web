package com.organizacion.componentes.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/planes")
public class PlanDeRehabilitacionController {

    @Autowired
    private PlanDeRehabilitacionService planDeRehabilitacionService;

    // Obtener todos los planes de rehabilitación
    @GetMapping
    public List<PlanDeRehabilitacion> obtenerTodosLosPlanes() {
        return planDeRehabilitacionService.obtenerTodosLosPlanes();
    }

    // Obtener un plan de rehabilitación por ID
    @GetMapping("/{id}")
    public ResponseEntity<PlanDeRehabilitacion> obtenerPlanPorId(@PathVariable Long id) {
        Optional<PlanDeRehabilitacion> plan = planDeRehabilitacionService.obtenerPlanPorId(id);
        if (plan.isPresent()) {
            return ResponseEntity.ok(plan.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo plan de rehabilitación
    @PostMapping
    public PlanDeRehabilitacion crearPlan(@RequestBody PlanDeRehabilitacion plan) {
        return planDeRehabilitacionService.crearPlan(plan);
    }

    // Actualizar un plan de rehabilitación existente
    @PutMapping("/{id}")
    public ResponseEntity<PlanDeRehabilitacion> actualizarPlan(@PathVariable Long id, @RequestBody PlanDeRehabilitacion planDetalles) {
        Optional<PlanDeRehabilitacion> planActualizado = planDeRehabilitacionService.actualizarPlan(id, planDetalles);
        if (planActualizado.isPresent()) {
            return ResponseEntity.ok(planActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un plan de rehabilitación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        boolean eliminado = planDeRehabilitacionService.eliminarPlan(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
