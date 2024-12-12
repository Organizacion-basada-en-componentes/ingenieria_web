package com.organizacion.componentes.back.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Cita {

    public Cita() {
    }

    @Builder
    public Cita(LocalDateTime fechaHora, String motivo, Medico medico, Paciente paciente) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;

    @Column(nullable = true)
    private String motivo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;  // Relación con la entidad Medico

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;  // Relación con la entidad Paciente

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
