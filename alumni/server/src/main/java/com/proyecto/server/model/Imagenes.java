package com.proyecto.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "UTIC.UZLTIMAGENES")
public class Imagenes {

    @Id
    @Column(name = "UZLTIMAGENES_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTIMAGENES_TIPO")
    private String tipo;

    @Column(name = "UZLTIMAGENES_NOMBRE")
    private String nombre;

    @Column(name = "UZLTIMAGENES_CONTENIDO")
    @Type(type="text")
    private String adjunto;


    public Imagenes() {
        super();
    }

    public Imagenes(String tipo, String nombre, String adjunto) {
        super();
        this.tipo = tipo;
        this.nombre = nombre;
        this.adjunto = adjunto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }
}
