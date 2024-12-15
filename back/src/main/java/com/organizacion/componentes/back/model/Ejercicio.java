package com.organizacion.componentes.back.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.organizacion.componentes.back.model.PlanDeRehabilitacion;

@Entity
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int series;

    @Column(nullable = false)
    private int repeticiones;

    @Column(nullable = true)
    private String descripcion;

    @Column(nullable = true)
    private Boolean completado;

    @Column(nullable = true)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = true)
    @JsonBackReference
    private PlanDeRehabilitacion planDeRehabilitacion;


    public Ejercicio() {
    }

    public Ejercicio(String nombre, int series, int repeticiones, String descripcion, Boolean completado, String comentario) {
        this.nombre = nombre;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descripcion = descripcion;
        this.completado = completado;
        this.comentario = comentario;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanDeRehabilitacion getPlanDeRehabilitacion() {
        return planDeRehabilitacion;
    }

    public void setPlanDeRehabilitacion(PlanDeRehabilitacion planDeRehabilitacion) {
        this.planDeRehabilitacion = planDeRehabilitacion;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
