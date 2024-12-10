package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Obtener un paciente por su DNI
    @GetMapping("/{dni}")
    public Paciente getPaciente(@PathVariable String dni) {
        return pacienteService.getPacienteById(dni);
    }

    // Crear un nuevo paciente


    // Eliminar un paciente por su DNI
    @DeleteMapping("/{dni}")
    public void deletePaciente(@PathVariable String dni) {
        pacienteService.deletePaciente(dni);
    }
}
