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
    private RepositoryMedico medicoRepository;

    public Medico saveMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Optional<Medico> getMedicoByDni(String dni) {
        return Optional.ofNullable(medicoRepository.findByDni(dni));
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public void deleteMedico(String dni) {
        medicoRepository.deleteById(dni);  // Cambiado de Long a String
    }
}
