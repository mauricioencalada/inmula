package com.proyecto.server.controller;

import com.proyecto.server.model.Carrera;
import com.proyecto.server.services.CarreraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/carrera")
public class CarreraController {

    @Autowired
    private CarreraServices _carreraServices;

    //OBTENER LOS BLOG POR EL ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable("id") Long id) {
        Carrera carrera = _carreraServices.buscarPorId(id);
        if (carrera == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Carrera>(carrera, HttpStatus.OK);
    }

    //OBTENER TODOS LOS BLOG
    @RequestMapping(value = "/obtenerCarreras", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Carrera>> obtenerCarreras() {
        List<Carrera> carreras = new ArrayList<Carrera>();

        carreras = _carreraServices.obtenerTodos();
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }


    //CREAR NUEVO BLOG
    @RequestMapping(value = "/crearCarrera", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearCarrera(@RequestBody Carrera carrera, UriComponentsBuilder uriComponentsBuilder) {

        if (carrera.getIdBanner().equals(null)) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _carreraServices.crearCarrera(carrera);
        return new ResponseEntity<Carrera>(carrera, HttpStatus.CREATED);
    }


    //ACTUALIZAR ContenidoBlog
    @RequestMapping(value = "/actualizarCarrera/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actualizarCarrera(@PathVariable("id") Long id, @RequestBody Carrera carrera) {

        Carrera carreraUpdate = _carreraServices.buscarPorId(id);
        if (carreraUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        carreraUpdate.setIdBanner(carrera.getIdBanner());
        return new ResponseEntity<Carrera>(carreraUpdate, HttpStatus.OK);
    }

}
