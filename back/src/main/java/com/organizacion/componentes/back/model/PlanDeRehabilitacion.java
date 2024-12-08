package com.organizacion.componentes.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Para ignorar el serializador de Hibernate
public class PlanDeRehabilitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(optional = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    private Medico medico;

    @Column(nullable = false)
    private boolean completado; // Campo para indicar si el paciente completó el plan

    @Column(nullable = false)
    private LocalDateTime fechaInicio; // Fecha de inicio del plan de rehabilitación

    private String comentario; // Comentario para el feedback del paciente

    // Relación ManyToMany con Ejercicio
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plan_ejercicio", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "ejercicio_id"))
    private List<Ejercicio> ejercicios;

    // Constructor vacío
    public PlanDeRehabilitacion() {
    }

    // Constructor con parámetros
    public PlanDeRehabilitacion(String nombre, Paciente paciente, Medico medico, boolean completado,
            LocalDateTime fechaInicio, String comentario, List<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.paciente = paciente;
        this.medico = medico;
        this.completado = completado;
        this.fechaInicio = fechaInicio;
        this.comentario = comentario;
        this.ejercicios = ejercicios;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
