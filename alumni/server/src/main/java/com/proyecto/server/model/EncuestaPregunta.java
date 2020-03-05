package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "UTIC.UZLTENPREGUNTA")
public class EncuestaPregunta implements Serializable {

    @Id
    @Column(name = "UZLTENPREGUNTA_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTENPREGUNTA_FECHARESPUESTA")
    @Temporal(TemporalType.DATE)
    private Calendar fechaRespuesta;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTRESPUESTA_CODE")
    private Respuesta respuesta;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "UZLTENCUESTA_CODE")
    private Encuesta encuesta;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTPREGUNTA_CODE")
    private Preguntas preguntas;

    @Column(name = "UZLTENPREGUNTA_ESTADO")
    private String estado;

    @Column(name = "UZLTENPREGUNTA_ADMINISTRADOR")
    private String administrador;

    public EncuestaPregunta(){
        super();
    }

    public EncuestaPregunta(Calendar fechaRespuesta, Respuesta respuesta, Encuesta encuesta, Preguntas preguntas, String estado, String  administrador){
        super();
        this.fechaRespuesta = fechaRespuesta;
        this.respuesta = respuesta;
        this.encuesta = encuesta;
        this.preguntas =preguntas;
        this.estado = estado;
        this.administrador = administrador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Calendar fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Preguntas getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Preguntas preguntas) {
        this.preguntas = preguntas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
