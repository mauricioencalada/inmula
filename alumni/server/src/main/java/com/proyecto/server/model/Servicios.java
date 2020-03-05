package com.proyecto.server.model;

import javax.persistence.*;

@Entity
@Table(name = "UTIC.UZLTSERVICIO")
public class Servicios {

    @Id
    @Column(name = "UZLTSERVICIO_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTSERVICIO_DESCRIPCION")
    private String descripcion;

    @Column(name = "UZLTSERVICIO_FECHAINICIO")
    private String fechaInicio;

    @Column(name = "UZLTSERVICIO_FECHAFIN")
    private String fechaFin;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IMAGENES_SERVICIO")
    private Imagenes imagenes;

    public Servicios() {
        super();
    }

    public Servicios(String descripcion, String fechaInicio, String fechaFin, Imagenes imagenes) {
        super();
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.imagenes = imagenes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Imagenes getImagenes() {
        return imagenes;
    }

    public void setImagenes(Imagenes imagenes) {
        this.imagenes = imagenes;
    }
}
