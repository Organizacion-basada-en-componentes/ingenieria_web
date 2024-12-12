package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    public ResponseEntity<PlanDeRehabilitacion> crearPlan(@RequestBody PlanDeRehabilitacion plan) {
        PlanDeRehabilitacion nuevoPlan = planService.crearPlan(plan);
        return ResponseEntity.ok(nuevoPlan);
    }

    @GetMapping
    public List<PlanDeRehabilitacion> obtenerTodos() {
        return planService.obtenerTodos();
    }
}
