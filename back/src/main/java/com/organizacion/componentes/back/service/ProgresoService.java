package com.organizacion.componentes.back.service;
import com.organizacion.componentes.back.model.Progreso;
import com.organizacion.componentes.back.repository.ProgresoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgresoService {

    @Autowired
    private ProgresoRepository progresoRepository;

    public List<Progreso> getAllProgresos() {
        return progresoRepository.findAll();
    }

    public Progreso getProgresoById(Long id) {
        return progresoRepository.findById(id).orElseThrow(() -> new RuntimeException("Progreso no encontrado"));
    }

    public Progreso createProgreso(Progreso progreso) {
        return progresoRepository.save(progreso);
    }

    public Progreso updateProgreso(Long id, Progreso progreso) {
        Progreso existingProgreso = getProgresoById(id);
        existingProgreso.setDescripcion(progreso.getDescripcion());
        return progresoRepository.save(existingProgreso);
    }

    public void deleteProgreso(Long id) {
        progresoRepository.deleteById(id);
    }
}
