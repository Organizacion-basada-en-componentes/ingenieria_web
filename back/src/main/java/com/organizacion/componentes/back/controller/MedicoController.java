package com.organizacion.componentes.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.service.MedicoService;

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

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoByDni(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoService.getAllMedicos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/pacientes")
    public ResponseEntity<List<Paciente>> getPacientesByMedico(@PathVariable Long id) {
        Optional<Medico> medicoOptional = medicoService.getMedicoById(id);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();
            return ResponseEntity.ok(medico.getPacientes());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
