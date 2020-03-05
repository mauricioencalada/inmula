package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTCAENCUESTA")
public class TipoEncuesta implements Serializable {

    @Id
    @Column(name = "UZLTCAENCUESTA_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTCAENCUESTA_NOMBRE")
    private String nombre;

    @Column(name = "UZLTCAENCUESTA_DESCRIPCION")
    private String descripcion;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTCARRERA_CODE")
    private Carrera carrera;


    public TipoEncuesta() {

        super();
    }

    public TipoEncuesta(String descripcion, String nombre, Carrera carrera) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.carrera = carrera;
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

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
