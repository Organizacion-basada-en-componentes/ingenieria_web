package com.organizacion.componentes.back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Mensaje;
import com.organizacion.componentes.back.repository.MensajeRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    // Método para guardar un mensaje
    public Mensaje saveMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    // Método para obtener todos los mensajes con paginación
    public Page<Mensaje> getAllMensajes(Pageable pageable) {
        return mensajeRepository.findAll(pageable);
    }

    // Método para obtener un mensaje por ID
    public Optional<Mensaje> getMensajeById(Long id) {
        return mensajeRepository.findById(id);
    }

    // Método para eliminar un mensaje
    public void deleteMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }
}
