package com.organizacion.componentes.back.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Medico extends User {

    @Column(nullable = false)
    private String dni;

    private String nombre;

    private String especialidad;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Cita> citas;

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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

