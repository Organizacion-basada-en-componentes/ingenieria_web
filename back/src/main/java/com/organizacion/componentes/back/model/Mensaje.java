package com.organizacion.componentes.back.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id") // Cambiado de "Medico_id" a "medico_id" para seguir las convenciones de nombre
    private Medico medico; // Cambiado el nombre del atributo a "medico"

    // Enum para representar el estado del mensaje
    public enum Estado {
        ENVIADO,
        RECIBIDO,
        ABIERTO
    }

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private Boolean emergencia; // Indica si el mensaje es prioritario

    private Boolean enviado; // Indica si el mensaje fue enviado correctamente

    public Mensaje() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() { // Método getter actualizado
        return medico;
    }

    public void setMedico(Medico medico) { // Método setter actualizado
        this.medico = medico;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(Boolean emergencia) {
        this.emergencia = emergencia;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }
}
