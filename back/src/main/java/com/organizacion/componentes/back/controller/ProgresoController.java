package com.organizacion.componentes.back.controller;
import com.organizacion.componentes.back.model.Progreso;
import com.organizacion.componentes.back.service.ProgresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progresos")
public class ProgresoController {

    @Autowired
    private ProgresoService progresoService;

    @GetMapping
    public List<Progreso> getAllProgresos() {
        return progresoService.getAllProgresos();
    }

    @GetMapping("/{id}")
    public Progreso getProgreso(@PathVariable Long id) {
        return progresoService.getProgresoById(id);
    }

    @PostMapping
    public Progreso createProgreso(@RequestBody Progreso progreso) {
        return progresoService.createProgreso(progreso);
    }

    @PutMapping("/{id}")
    public Progreso updateProgreso(@PathVariable Long id, @RequestBody Progreso progreso) {
        return progresoService.updateProgreso(id, progreso);
    }

    @DeleteMapping("/{id}")
    public void deleteProgreso(@PathVariable Long id) {
        progresoService.deleteProgreso(id);
    }
}
