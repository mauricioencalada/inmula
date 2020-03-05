package com.proyecto.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;

import oracle.sql.CLOB;

@Entity
@Table(name = "UTIC.UZLTPERSONA")
public class Persona implements Serializable {

    @Id
    @Column(name = "UZLTPERSONA_CODE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTPERSONA_BANNER")
    private String personaBanner;

    @Column(name = "UZLTPERSONA_CONTRASENIA")
    private String password;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "UZLTROL_CODE")
    private Roles roles;

    public Persona() {
        super();
    }

    public Persona(String personaBanner, Roles roles) {
        super();
        this.personaBanner = personaBanner;
        this.roles = roles;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonaBanner() {
        return personaBanner;
    }

    public void setPersonaBanner(String personaBanner) {
        this.personaBanner = personaBanner;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




