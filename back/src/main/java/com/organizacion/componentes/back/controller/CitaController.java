package com.organizacion.componentes.back.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Cita;
import com.organizacion.componentes.back.service.CitaService;


@RestController
@RequestMapping("/auth/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Obtener todas las citas
    @GetMapping
    public List<Cita> getAllCitas() {
        return citaService.getAllCitas();
    }

    // Obtener cita por ID
    @GetMapping("/{id}")
    public Cita getCita(@PathVariable Long id) {
        return citaService.getCitaById(id);
    }

    // Crear una nueva cita
    @PostMapping
    public Cita createCita(@RequestBody Cita cita) {
        return citaService.createCita(cita);
    }

    // Actualizar una cita existente
    @PutMapping("/{id}")
    public Cita updateCita(@PathVariable Long id, @RequestBody Cita cita) {
        return citaService.updateCita(id, cita);
    }

    // Eliminar una cita
    @DeleteMapping("/{id}")
    public void deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
    }

    // Obtener todas las citas de un médico por su DNI
    @GetMapping("/medico/{dni}")
    public List<Cita> getCitasByMedico(@PathVariable String dni) {
        return citaService.getCitasByMedico(dni);
    }

    // Obtener todas las citas de un paciente por su DNI
    @GetMapping("/paciente/{dni}")
    public List<Cita> getCitasByPaciente(@PathVariable String dni) {
        return citaService.getCitasByPaciente(dni);
    }
}
