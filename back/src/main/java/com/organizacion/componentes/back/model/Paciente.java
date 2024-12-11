package com.organizacion.componentes.back.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;


@Entity
public class Paciente {

  
    public Paciente() {
    }

  
    @Builder
    public Paciente(String dni, String nombre, Date fechaNacimiento, Usuario usuario) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public long getId() {
        return id;
    }

    @Column(unique = true, nullable = false)
    private String dni;  // 'dni' es la clave primaria

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private Date fechaNacimiento;


    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false) // 'usuario_id' es la clave foránea en la tabla 'Medico'
    private Usuario usuario;


    // @OneToMany(mappedBy = "paciente")
    // @JsonIgnoreProperties({"paciente"}) 
    // private List<Cita> citas;

    // @OneToMany(mappedBy = "paciente")
    // private List<Progreso> progresos;

    // @OneToMany(mappedBy = "paciente")
    // @JsonIgnoreProperties({"paciente"})  // Evita la referencia circular
    // private List<Ejercicio> ejercicios;
    // public Paciente(
    //     List<Cita> citas, 
    //     String dni, 
    //     List<Ejercicio> ejercicios, 
    //     LocalDate fechaNacimiento, 
    //     String nombre, 
    //     List<Progreso> progresos, 
    //     Usuario usuario) 
    // {
    //     this.citas = citas;
    //     this.dni = dni;
    //     this.ejercicios = ejercicios;
    //     this.fechaNacimiento = fechaNacimiento;
    //     this.nombre = nombre;
    //     this.progresos = progresos;
    //     this.usuario = usuario;
    // }

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


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // public List<Cita> getCitas() {
    //     return citas;
    // }

    // public void setCitas(List<Cita> citas) {
    //     this.citas = citas;
    // }

    // public List<Progreso> getProgresos() {
    //     return progresos;
    // }

    // public void setProgresos(List<Progreso> progresos) {
    //     this.progresos = progresos;
    // }

    // public List<Ejercicio> getEjercicios() {
    //     return ejercicios;
    // }

    // public void setEjercicios(List<Ejercicio> ejercicios) {
    //     this.ejercicios = ejercicios;
    // }
}
