package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.repository.RepositoryMedico;

@Service
public class MedicoService {

    @Autowired
    private RepositoryMedico repositoryMedico;

    // Obtener todos los médicos
    public List<Medico> obtenerTodosLosMedicos() {
        return repositoryMedico.findAll();
    }

    // Obtener un médico por ID
    public Optional<Medico> obtenerMedicoPorId(Long id) {
        return repositoryMedico.findById(id);
    }

    // Crear un nuevo médico
    public Medico crearMedico(Medico medico) {
        return repositoryMedico.saveAndFlush(medico);
    }

    // Actualizar un médico existente
    public Optional<Medico> actualizarMedico(Long id, Medico medicoDetalles) {
        Optional<Medico> medicoOpt = repositoryMedico.findById(id);
        if (medicoOpt.isPresent()) {
            Medico medicoExistente = medicoOpt.get();
            medicoExistente.setEspecialidad(medicoDetalles.getEspecialidad());
            medicoExistente.setNombre(medicoDetalles.getNombre());
            return Optional.of(repositoryMedico.save(medicoExistente));
        }
        return Optional.empty();
    }

    // Eliminar un médico por ID
    public boolean eliminarMedico(Long id) {
        if (repositoryMedico.existsById(id)) {
            repositoryMedico.deleteById(id);
            return true;
        }
        return false;
    }
}
