package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTTIPOBLOG")
public class TipoBlog implements Serializable {

    @Id
    @Column(name = "UZLTTIPOBLOG_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTTIPOBLOG_DESCRIPCION")
    private String descripcion;

    public TipoBlog(){
        super();
    }

    public TipoBlog(String descripcion){
        super();
        this.descripcion =descripcion;
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
