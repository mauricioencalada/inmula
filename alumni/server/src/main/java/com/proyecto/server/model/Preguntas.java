package com.proyecto.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTPREGUNTA")
public class Preguntas implements Serializable {

    @Id
    @Column(name = "UZLTPREGUNTA_CODE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTPREGUNTA_DESCRIPCION", nullable = false, length = 1000)
    private String descripcion;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTTIPREGUNTA_CODE")
    private TipoPregunta tipoPregunta;

    public Preguntas() {
        super();
    }

    public Preguntas(String descripcion, TipoPregunta tipoPregunta) {
        super();
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
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

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }
}
