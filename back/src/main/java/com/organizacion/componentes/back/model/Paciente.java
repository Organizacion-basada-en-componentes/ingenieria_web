package com.organizacion.componentes.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Paciente {

    @Id
    private String dni;  // Ahora 'dni' es la clave primaria

    @Column(nullable = false)
    private String username;  // Nombre de usuario del paciente

    @Column(nullable = false)
    private String contraseña;  // Contraseña del paciente

    private String nombre;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<Progreso> progresos;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnoreProperties({"paciente"})  // Evita la referencia circular
    private List<Ejercicio> ejercicios;

    // Getters y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
