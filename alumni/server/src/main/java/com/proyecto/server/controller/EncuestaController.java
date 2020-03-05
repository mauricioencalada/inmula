package com.proyecto.server.controller;

import com.proyecto.server.model.Encuesta;
import com.proyecto.server.services.EncuestaServices;
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
@RequestMapping(value = "/encuesta")
public class EncuestaController {

    @Autowired
    private EncuestaServices _encuestaServices;

    //OBTENER PERSONA POR ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Encuesta> getEncuestaById(@PathVariable("id") Long id) {
        Encuesta encuesta = _encuestaServices.buscarPorId(id);
        if (encuesta == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Encuesta>(encuesta, HttpStatus.OK);
    }

    //OBTENER TODAS LAS PERSONA
    @RequestMapping(value = "/obtenerEncuestas", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Encuesta>> obtenerEncuestas() {
        List<Encuesta> encuestas = new ArrayList<Encuesta>();

        encuestas = _encuestaServices.obtenerTodas();
        return new ResponseEntity<List<Encuesta>>(encuestas, HttpStatus.OK);
    }

    // CREAR NUEVA PERSONAS
    @RequestMapping(value = "/crearEncuesta", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearEncuesta(@RequestBody Encuesta encuesta, UriComponentsBuilder uriComponentsBuilder) {

        if (encuesta.getNombre().equals(null) || encuesta.getNombre().isEmpty()) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _encuestaServices.crearEncuesta(encuesta);
        return new ResponseEntity<Encuesta>(encuesta, HttpStatus.CREATED);
    }

    //ACTUALIZAR PERSONA EXISTENTE
    @RequestMapping(value = "/actualizarEncuesta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actulizarEncuesta(@PathVariable("id") Long id, @RequestBody Encuesta encuesta) {

        Encuesta encuestaUpdate = _encuestaServices.buscarPorId(id);
        if (encuestaUpdate == null) {
            return new ResponseEntity("No se encuentra encuesta", HttpStatus.NOT_FOUND);
        }

        encuestaUpdate.setDescripcion(encuesta.getDescripcion());
        encuestaUpdate.setEstado(encuesta.getEstado());
        encuestaUpdate.setFechaFin(encuesta.getFechaFin());
        encuestaUpdate.setFechaInicio(encuesta.getFechaInicio());
        encuestaUpdate.setNombre(encuesta.getNombre());
        encuestaUpdate.setObjetivo(encuesta.getObjetivo());
        encuestaUpdate.setTipoEncuesta(encuesta.getTipoEncuesta());
        _encuestaServices.actualizarEncuesta(encuestaUpdate);

        return new ResponseEntity<Encuesta>(encuestaUpdate, HttpStatus.OK);
    }

}
