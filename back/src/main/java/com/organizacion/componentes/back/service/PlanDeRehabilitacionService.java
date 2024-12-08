package com.organizacion.componentes.back.service;

import com.organizacion.componentes.back.model.PlanDeRehabilitacion;
import com.organizacion.componentes.back.model.Ejercicio;
import com.organizacion.componentes.back.repository.PlanDeRehabilitacionRepository;
import com.organizacion.componentes.back.repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanDeRehabilitacionService {

    @Autowired
    private PlanDeRehabilitacionRepository planDeRehabilitacionRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    // Obtener todos los planes de rehabilitación
    public List<PlanDeRehabilitacion> obtenerTodosLosPlanes() {
        return planDeRehabilitacionRepository.findAll();
    }

    // Obtener un plan de rehabilitación por ID
    public Optional<PlanDeRehabilitacion> obtenerPlanPorId(Long id) {
        return planDeRehabilitacionRepository.findById(id);
    }

    // Crear un nuevo plan de rehabilitación
    public PlanDeRehabilitacion crearPlan(PlanDeRehabilitacion plan) {
        return planDeRehabilitacionRepository.save(plan);
    }

    // Actualizar un plan de rehabilitación existente
    public Optional<PlanDeRehabilitacion> actualizarPlan(Long id, PlanDeRehabilitacion planDetalles) {
        return planDeRehabilitacionRepository.findById(id)
                .map(plan -> {
                    plan.setNombre(planDetalles.getNombre());
                    plan.setPaciente(planDetalles.getPaciente());
                    plan.setMedico(planDetalles.getMedico());
                    plan.setCompletado(planDetalles.isCompletado());
                    plan.setFechaInicio(planDetalles.getFechaInicio());
                    plan.setComentario(planDetalles.getComentario());
                    plan.setEjercicios(planDetalles.getEjercicios()); // Actualiza los ejercicios
                    return planDeRehabilitacionRepository.save(plan);
                });
    }

    // Eliminar un plan de rehabilitación por ID
    public boolean eliminarPlan(Long id) {
        return planDeRehabilitacionRepository.findById(id)
                .map(plan -> {
                    planDeRehabilitacionRepository.delete(plan);
                    return true;
                }).orElse(false);
    }

    // Agregar ejercicios a un plan de rehabilitación
    public PlanDeRehabilitacion agregarEjerciciosAlPlan(Long planId, List<Long> ejercicioIds) {
        return planDeRehabilitacionRepository.findById(planId)
                .map(plan -> {
                    List<Ejercicio> ejercicios = (List<Ejercicio>) ejercicioRepository.findAllById(ejercicioIds);

                    // Verificar que los ejercicios se encuentren y no estén vacíos
                    if (ejercicios.isEmpty()) {
                        return null; // Si no se encuentran ejercicios, retorna null
                    }

                    plan.getEjercicios().addAll(ejercicios); // Agregar los ejercicios al plan
                    return planDeRehabilitacionRepository.save(plan); // Guardar y devolver el plan actualizado
                }).orElse(null); // Si no se encuentra el plan, retorna null
    }
}
