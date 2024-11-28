package com.organizacion.componentes.back.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Paciente extends User {

    @Column(nullable = false)
    private String dni;

    private String nombre;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<PlanDeRehabilitacion> planesDeRehabilitacion;

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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<PlanDeRehabilitacion> getPlanesDeRehabilitacion() {
        return planesDeRehabilitacion;
    }

    public void setPlanesDeRehabilitacion(List<PlanDeRehabilitacion> planesDeRehabilitacion) {
        this.planesDeRehabilitacion = planesDeRehabilitacion;
    }
}

