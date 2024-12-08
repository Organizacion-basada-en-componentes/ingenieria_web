package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.service.MedicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // Obtener todos los médicos
    @GetMapping
    public List<Medico> obtenerTodosLosMedicos() {
        return medicoService.obtenerTodosLosMedicos();
    }

    // Obtener un médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorId(id);
        if (medico.isPresent()) {
            return ResponseEntity.ok(medico.get());
        } else {
            return ResponseEntity.notFound().build(); // 404 si no se encuentra el médico
        }
    }

    // Crear un nuevo médico
    @PostMapping
    public ResponseEntity<Medico> crearMedico(@RequestBody @Valid Medico medico) {
        try {
            Medico nuevoMedico = medicoService.crearMedico(medico);
            return ResponseEntity.status(201).body(nuevoMedico); // 201 para creación exitosa
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null); // Responder con 400 si hay un error en la creación
        }
    }

    // Actualizar un médico existente
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id, @RequestBody Medico medicoDetalles) {
        Optional<Medico> medicoActualizado = medicoService.actualizarMedico(id, medicoDetalles);
        if (medicoActualizado.isPresent()) {
            return ResponseEntity.ok(medicoActualizado.get());
        } else {
            return ResponseEntity.notFound().build(); // 404 si no se encuentra el médico
        }
    }

    // Eliminar un médico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMedico(@PathVariable Long id) {
        boolean eliminado = medicoService.eliminarMedico(id);
        if (eliminado) {
            return ResponseEntity.ok("Médico eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build(); // 404 si no se encuentra el médico
        }
    }
}
