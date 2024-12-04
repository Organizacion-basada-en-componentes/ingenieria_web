package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Resultado;
import com.organizacion.componentes.back.repository.ResultadoRepository;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    // Obtener todos los resultados
    public List<Resultado> obtenerTodosLosResultados() {
        return resultadoRepository.findAll();
    }

    // Obtener un resultado por ID
    public Optional<Resultado> obtenerResultadoPorId(Long id) {
        return resultadoRepository.findById(id);
    }

    // Crear un nuevo resultado
    public Resultado crearResultado(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    // Actualizar un resultado existente
    public Optional<Resultado> actualizarResultado(Long id, Resultado resultadoDetalles) {
        if (resultadoRepository.existsById(id)) {
            resultadoDetalles.setId(id);
            return Optional.of(resultadoRepository.save(resultadoDetalles));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar un resultado por ID
    public boolean eliminarResultado(Long id) {
        if (resultadoRepository.existsById(id)) {
            resultadoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
