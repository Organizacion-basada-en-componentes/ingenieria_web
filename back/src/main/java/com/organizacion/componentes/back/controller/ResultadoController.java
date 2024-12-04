package com.organizacion.componentes.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Resultado;
import com.organizacion.componentes.back.service.ResultadoService;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    // Obtener todos los resultados
    @GetMapping
    public List<Resultado> obtenerTodosLosResultados() {
        return resultadoService.obtenerTodosLosResultados();
    }

    // Obtener un resultado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Resultado> obtenerResultadoPorId(@PathVariable Long id) {
        Optional<Resultado> resultado = resultadoService.obtenerResultadoPorId(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo resultado
    @PostMapping
    public Resultado crearResultado(@RequestBody Resultado resultado) {
        return resultadoService.crearResultado(resultado);
    }

    // Actualizar un resultado existente
    @PutMapping("/{id}")
    public ResponseEntity<Resultado> actualizarResultado(@PathVariable Long id, @RequestBody Resultado resultadoDetalles) {
        Optional<Resultado> resultadoActualizado = resultadoService.actualizarResultado(id, resultadoDetalles);
        if (resultadoActualizado.isPresent()) {
            return ResponseEntity.ok(resultadoActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un resultado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResultado(@PathVariable Long id) {
        boolean eliminado = resultadoService.eliminarResultado(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
