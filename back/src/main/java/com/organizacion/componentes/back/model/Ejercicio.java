package com.organizacion.componentes.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    private Long id;  // Clave primaria para Ejercicio

    private String nombre;      // Nombre del ejercicio
    private String descripcion; // Descripción del ejercicio
    private int series;         // Número de series
    private int repeticiones;   // Número de repeticiones

    @ManyToOne
    @JoinColumn(name = "paciente_dni", referencedColumnName = "dni", nullable = false)
    @JsonIgnoreProperties({"citas", "progresos", "ejercicios"})  // Evita la serialización de otras propiedades
    private Paciente paciente;  // Relación con Paciente

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @JsonProperty("paciente_dni")
    public String getPacienteDni() {
        return paciente != null ? paciente.getDni() : null;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
