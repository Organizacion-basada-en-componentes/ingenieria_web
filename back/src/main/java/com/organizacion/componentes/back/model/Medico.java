package com.organizacion.componentes.back.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @Column(nullable = false)
    private String especialidad;

  
 
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
