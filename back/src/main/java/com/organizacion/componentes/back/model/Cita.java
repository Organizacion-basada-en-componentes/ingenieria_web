package com.organizacion.componentes.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Asumimos que 'id' es la clave primaria para Cita

    private LocalDateTime fechaHora;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "medico_dni", referencedColumnName = "dni", nullable = false)
    private Medico medico;  // Relación con Medico (aquí estamos usando 'dni' como referencia)

    @ManyToOne
    @JoinColumn(name = "paciente_dni", referencedColumnName = "dni", nullable = false)
    private Paciente paciente;  // Relación con Paciente

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
