package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "UTIC.UZLTRESPUESTA")
public class Respuesta implements Serializable {

    @Id
    @Column(name = "UZLTRESPUESTA_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTRESPUESTA_RESPUESTA")
    private String respuesta;

    @Column(name = "UZLTRESPUESTA_DESCRIPCION")
    private String descripcion;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTPERSONA_CODE")
    private Persona persona;


    public Respuesta(){
        super();
    }

    public  Respuesta(String respuesta,String descripcion, Persona persona){
        super();
        this.respuesta = respuesta;
        this.descripcion = descripcion;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
