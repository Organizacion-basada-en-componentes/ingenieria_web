package com.organizacion.componentes.back.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;


@Entity
public class Medico {

    public Medico() {
    }
    @Builder
    public Medico(String dni, String nombre, Usuario usuario, String especialidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.usuario = usuario;
        this.especialidad = especialidad;
    }

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public long getId() {
        return id;
    }

    @Column(unique = true, nullable = false)
    private String dni;  // 'dni' es la clave primaria

    @Column(nullable = false)
    private String nombre;

    @OneToOne
    
    @JoinColumn(name = "usuario_id", nullable = false) // 'usuario_id' es la clave foránea en la tabla 'Medico'
    private Usuario usuario;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    @Column(nullable = false)
    private String especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void addPaciente(Paciente paciente) {
        this.pacientes.add(paciente);
        paciente.setMedico(this);
    }


  
 
    // @OneToMany(mappedBy = "medico")
    // @JsonIgnore
    // private List<Cita> citas;

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


    // public List<Cita> getCitas() {
    //     return citas;
    // }

    // public void setCitas(List<Cita> citas) {
    //     this.citas = citas;
    // }
}