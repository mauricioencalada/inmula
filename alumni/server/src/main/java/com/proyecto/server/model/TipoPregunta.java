package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTTIPREGUNTA")
public class TipoPregunta implements Serializable {

    @Id
    @Column(name = "UZLTTIPREGUNTA")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTTIPREGUNTA_TIPREGUNTA")
    private String descripcion;

    public TipoPregunta() {
        super();
    }

    public TipoPregunta(String descripcion) {
        super();
        this.descripcion = descripcion;
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
}
