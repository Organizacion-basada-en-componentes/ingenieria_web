package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Videollamada;
import com.organizacion.componentes.back.repository.VideollamadaRepository;

@Service
public class VideollamadaService {

    @Autowired
    private VideollamadaRepository videollamadaRepository;

    // Obtener todas las videollamadas
    public List<Videollamada> obtenerTodasLasVideollamadas() {
        return videollamadaRepository.findAll();
    }

    // Obtener una videollamada por ID
    public Optional<Videollamada> obtenerVideollamadaPorId(Long id) {
        return videollamadaRepository.findById(id);
    }

    // Crear una nueva videollamada
    public Videollamada crearVideollamada(Videollamada videollamada) {
        return videollamadaRepository.save(videollamada);
    }

    // Actualizar una videollamada existente
    public Optional<Videollamada> actualizarVideollamada(Long id, Videollamada videollamadaDetalles) {
        if (videollamadaRepository.existsById(id)) {
            videollamadaDetalles.setId(id);
            return Optional.of(videollamadaRepository.save(videollamadaDetalles));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar una videollamada por ID
    public boolean eliminarVideollamada(Long id) {
        if (videollamadaRepository.existsById(id)) {
            videollamadaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
