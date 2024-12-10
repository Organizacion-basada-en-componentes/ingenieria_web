package com.organizacion.componentes.back.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;

@Entity
public class Paciente {

    @Builder
    public Paciente(String dni, String nombre, LocalDate fechaNacimiento, Usuario usuario, List<Cita> citas, List<Progreso> progresos, List<Ejercicio> ejercicios) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.citas = citas;
        this.progresos = progresos;
        this.ejercicios = ejercicios;
    }

    @Id
    private String dni;  // Ahora 'dni' es la clave primaria

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private LocalDate fechaNacimiento;


    @OneToOne(mappedBy = "paciente")
    @JsonIgnoreProperties({"paciente"}) 
    private Usuario usuario; 

    @OneToMany(mappedBy = "paciente")
    @JsonIgnoreProperties({"paciente"}) 
    private List<Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<Progreso> progresos;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnoreProperties({"paciente"})  // Evita la referencia circular
    private List<Ejercicio> ejercicios;

    public Paciente(List<Cita> citas, String dni, List<Ejercicio> ejercicios, LocalDate fechaNacimiento, String nombre, List<Progreso> progresos, Usuario usuario) {
        this.citas = citas;
        this.dni = dni;
        this.ejercicios = ejercicios;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.progresos = progresos;
        this.usuario = usuario;
    }

    // Getters y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Progreso> getProgresos() {
        return progresos;
    }

    public void setProgresos(List<Progreso> progresos) {
        this.progresos = progresos;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
