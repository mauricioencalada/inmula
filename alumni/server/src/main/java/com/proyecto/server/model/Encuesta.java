package com.proyecto.server.model;

import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTENCUESTA")
public class Encuesta implements Serializable {

    @Id
    @Column(name = "UZLTENCUESTA_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTENCUESTA_NOMBRE")
    private String nombre;

    @Column(name = "UZLTENCUESTA_DESCRIPCION")
    private String descripcion;

    @Column(name = "UZLTENCUESTA_FECHA_INICIO")
    private String fechaInicio;

    @Column(name = "UZLTENCUESTA_FECHA_FIN")
    private String fechaFin;

    @Column(name = "UZLTENCUESTA_OBJETIVO")
    private String objetivo;

    @Column(name = "UZLTENCUESTA_ESTADO")
    private String estado;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTCAENCUESTA_CODE")
    private TipoEncuesta tipoEncuesta;


    public Encuesta() {
        super();
    }

    public Encuesta(String nombre, String descripcion, String fechaFin, String fechaInicio, String objetivo, String estado, TipoEncuesta tipoEncuesta) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.objetivo = objetivo;
        this.estado = estado;
        this.tipoEncuesta = tipoEncuesta;
    }

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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoEncuesta getTipoEncuesta() {
        return tipoEncuesta;
    }

    public void setTipoEncuesta(TipoEncuesta tipoEncuesta) {
        this.tipoEncuesta = tipoEncuesta;
    }
}
