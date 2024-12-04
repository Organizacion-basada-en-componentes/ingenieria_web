package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Alerta;
import com.organizacion.componentes.back.repository.AlertaRepository;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    // Obtener todas las alertas
    public List<Alerta> obtenerTodasLasAlertas() {
        return alertaRepository.findAll();
    }

    // Obtener una alerta por ID
    public Optional<Alerta> obtenerAlertaPorId(Long id) {
        return alertaRepository.findById(id);
    }

    // Crear una nueva alerta
    public Alerta crearAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    // Actualizar una alerta existente
    public Optional<Alerta> actualizarAlerta(Long id, Alerta alertaDetalles) {
        if (alertaRepository.existsById(id)) {
            alertaDetalles.setId(id);
            return Optional.of(alertaRepository.save(alertaDetalles));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar una alerta por ID
    public boolean eliminarAlerta(Long id) {
        if (alertaRepository.existsById(id)) {
            alertaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
