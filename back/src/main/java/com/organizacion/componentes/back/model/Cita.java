package com.organizacion.componentes.back.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Para ignorar el serializador de Hibernate
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Cita() {
        // Constructor vacío
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    private LocalDateTime fecha;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @ManyToOne
    private User paciente;

    public User getPaciente() {
        return paciente;
    }

    public void setPaciente(User paciente) {
        this.paciente = paciente;
    }

    @ManyToOne
    private User profesionalMedico;

    public User getProfesionalMedico() {
        return profesionalMedico;
    }

    public void setProfesionalMedico(User profesionalMedico) {
        this.profesionalMedico = profesionalMedico;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public enum EstadoCita {
        PENDIENTE,
        CANCELADA,
        COMPLETADA
    }

    @Column(nullable = false)
    private EstadoCita estado;

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
}