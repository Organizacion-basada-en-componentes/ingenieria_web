package com.organizacion.componentes.back.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Medico {

    @Id
    private String dni;  // 'dni' es la clave primaria

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String especialidad;

    @Column(nullable = false, unique = true)
    private String username;  // Nombre de usuario único y obligatorio

    @Column(nullable = false)
    private String contraseña;  // Contraseña obligatoria

    @OneToMany(mappedBy = "medico")
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
