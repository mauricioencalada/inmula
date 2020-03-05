package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTCONBLOG")
public class ContenidoBlog implements Serializable {

    @Id
    @Column(name = "UZLTCONBLOG_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTCONBLOG_CONTENIDO")
    private String contenido;

    @Column(name = "UZLTCONBLOG_FECHAPUBLICA")
    private String fechaInicio;

    @Column(name = "UZLTCONBLOG_FECHAFINAL")
    private String fechaFin;

    @Column(name = "UZLTCONBLOG_SERIAL")
    private String serial;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK1_INV_UZLTPERSONA_CODE")
    private Persona persona;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK2_INV_UZLTTIPOBLOG_CODE")
    private TipoBlog tipoBlog;

    public ContenidoBlog(){
        super();
    }

    public ContenidoBlog(String contenido, String fechaInicio, String fechaFin, String serial, Persona persona, TipoBlog tipoBlog) {
        super();
        this.contenido = contenido;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.serial = serial;
        this.persona = persona;
        this.tipoBlog = tipoBlog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoBlog getTipoBlog() {
        return tipoBlog;
    }

    public void setTipoBlog(TipoBlog tipoBlog) {
        this.tipoBlog = tipoBlog;
    }
}
