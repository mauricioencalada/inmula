package com.proyecto.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UTIC.UZLTCARRERA")
public class Carrera implements Serializable {

    @Id
    @Column(name = "UZLTCARRERA_CODE")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "UZLTCARRERA_BANNER")
    private Long idBanner;

    public Carrera() {
        super();
    }

    public Carrera(Long idBanner) {
        super();
        this.idBanner = idBanner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBanner() {
        return idBanner;
    }

    public void setIdBanner(Long idBanner) {
        this.idBanner = idBanner;
    }
}
