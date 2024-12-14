package com.organizacion.componentes.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.service.CitaService;
import com.organizacion.componentes.back.service.PacienteService;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private PacienteService pacienteService;

    // Crear una nueva cita proporcionando el id del paciente
    @PostMapping("/{idPaciente}")
    public ResponseEntity<Cita> createCita(@PathVariable Long idPaciente, @RequestBody Cita cita) {
        // Obtener el paciente por su ID
        Paciente paciente = pacienteService.getPacienteById(idPaciente);
        
        if (paciente == null) {
            return ResponseEntity.notFound().build();  // Si el paciente no se encuentra, devolvemos 404
        }

        // Obtener el médico asociado al paciente
        cita.setPaciente(paciente);  // Asignar paciente a la cita
        cita.setMedico(paciente.getMedico());  // Obtener y asignar el médico del paciente

        // Guardar la cita
        Cita nuevaCita = citaService.createCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }

    // Obtener una cita por su ID
    @GetMapping("/{idCita}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long idCita) {
        Cita cita = citaService.getCitaById(idCita);

        if (cita == null) {
            return ResponseEntity.notFound().build(); // Si la cita no se encuentra, devolvemos 404
        }

        return ResponseEntity.ok(cita); // Devolver la cita encontrada
    }

    // Eliminar una cita por su ID
    @DeleteMapping("/{idCita}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long idCita) {
        boolean deleted = citaService.deleteCitaById(idCita);

        if (!deleted) {
            return ResponseEntity.notFound().build(); // Si la cita no existe, devolvemos 404
        }

        return ResponseEntity.noContent().build(); // Si se eliminó correctamente, devolvemos 204 No Content
    }
}
