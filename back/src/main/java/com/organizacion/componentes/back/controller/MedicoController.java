package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico savedMedico = medicoService.saveMedico(medico);
        return ResponseEntity.ok(savedMedico);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Medico> getMedicoByDni(@PathVariable String dni) {
        Optional<Medico> medico = medicoService.getMedicoByDni(dni);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoService.getAllMedicos();
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteMedico(@PathVariable String dni) {
        medicoService.deleteMedico(dni);
        return ResponseEntity.noContent().build();
    }
}
