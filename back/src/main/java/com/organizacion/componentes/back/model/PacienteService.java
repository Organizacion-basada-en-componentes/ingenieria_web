package com.organizacion.componentes.back.model;
   
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PacienteService {

    @Autowired
    RepositoryPaciente repositoryPaciente;

    public List<Paciente> getAllPacientes() {
        return repositoryPaciente.findAll();
    }

    public Paciente getPaciente(Long id) {
        return repositoryPaciente.getReferenceById(id);
    }

    public Paciente addPaciente(Paciente p) {
        return repositoryPaciente.saveAndFlush(p);
    }

    public void updatePaciente(Paciente p) {
        Paciente paciente = repositoryPaciente.getReferenceById(p.getId());
        paciente.setDni(p.getDni());
        paciente.setNombre(p.getNombre());
        paciente.setCitas(p.getCitas());
        paciente.setPlanesDeRehabilitacion(p.getPlanesDeRehabilitacion());
        repositoryPaciente.save(paciente);
    }

    public void removePaciente(Paciente p) {
        repositoryPaciente.delete(p);
    }

    public void removePacienteID(Long id) {
        repositoryPaciente.deleteById(id);
    }
}

    

