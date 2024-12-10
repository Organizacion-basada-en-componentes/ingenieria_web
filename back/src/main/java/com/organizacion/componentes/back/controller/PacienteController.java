package com.organizacion.componentes.back.controller;

import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Obtener un paciente por su DNI
    @GetMapping("/{dni}")
    public Paciente getPaciente(@PathVariable String dni) {
        return pacienteService.getPacienteById(dni);
    }

    // Crear un nuevo paciente
    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.createPaciente(paciente);
    }

    // Actualizar un paciente existente
    @PutMapping("/{dni}")
    public Paciente updatePaciente(@PathVariable String dni, @RequestBody Paciente paciente) {
        return pacienteService.updatePaciente(dni, paciente);
    }

    // Eliminar un paciente por su DNI
    @DeleteMapping("/{dni}")
    public void deletePaciente(@PathVariable String dni) {
        pacienteService.deletePaciente(dni);
    }
}
