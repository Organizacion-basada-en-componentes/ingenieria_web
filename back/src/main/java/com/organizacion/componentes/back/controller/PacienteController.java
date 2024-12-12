package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.service.PacienteService;

@RestController
@RequestMapping("/auth/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Obtener todos los pacientes
    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    // Obtener un paciente por su id
    @GetMapping("/{id}")
    public Paciente getPaciente(@PathVariable Long id) {
        return pacienteService.getPacienteById(id);
    }

    // Obtener el ID del médico asociado a un paciente
    @GetMapping("/{id}/medico")
    public ResponseEntity<Long> getMedicoIdByPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteService.getPacienteById(id); // Obtén el paciente por su ID
        if (paciente.getMedico() != null) { // Verifica si el paciente tiene un médico asociado
            return ResponseEntity.ok(paciente.getMedico().getId()); // Devuelve el ID del médico
        } else {
            return ResponseEntity.notFound().build(); // Si no hay médico asociado, devuelve un 404
        }
    }



    // Eliminar un paciente por su DNI
    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
    }
     @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
        Paciente pacienteGuardado = pacienteService.crearPaciente(paciente);
        return ResponseEntity.ok(pacienteGuardado);
    }

    
}