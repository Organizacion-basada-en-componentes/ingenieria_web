package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Registro;
import com.organizacion.componentes.back.repository.RegistroRepository;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    // Obtener todos los registros
    public List<Registro> obtenerTodosLosRegistros() {
        return registroRepository.findAll();
    }

    // Obtener un registro por ID
    public Optional<Registro> obtenerRegistroPorId(Long id) {
        return registroRepository.findById(id);
    }

    // Crear un nuevo registro
    public Registro crearRegistro(Registro registro) {
        return registroRepository.save(registro);
    }

    // Actualizar un registro existente
    public Optional<Registro> actualizarRegistro(Long id, Registro registroDetalles) {
        if (registroRepository.existsById(id)) {
            registroDetalles.setId(id);
            return Optional.of(registroRepository.save(registroDetalles));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar un registro por ID
    public boolean eliminarRegistro(Long id) {
        if (registroRepository.existsById(id)) {
            registroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
