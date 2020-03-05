package com.proyecto.server.controller;

import com.proyecto.server.model.TipoEncuesta;
import com.proyecto.server.services.TipoEncuestaServices;
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
@RequestMapping(value = "/tipoEncuesta")
public class TipoEncuestaController {

    @Autowired
    private TipoEncuestaServices _tipoEncuestaServices;

    //OBTENER PREGUNTAS POR ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<TipoEncuesta> getTipoEncuestaById(@PathVariable("id") Long id) {
        TipoEncuesta tipoEncuesta = _tipoEncuestaServices.buscarPorId(id);
        if (tipoEncuesta == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TipoEncuesta>(tipoEncuesta, HttpStatus.OK);
    }

    //OBTENER TODAS LAS PREGUNTAS
    @RequestMapping(value = "/obtenerTipoEncuesta", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<TipoEncuesta>> obtenerTipoEncuestas() {
        List<TipoEncuesta> tipoEncuestas = new ArrayList<TipoEncuesta>();

        tipoEncuestas = _tipoEncuestaServices.obtenerTodas();
        return new ResponseEntity<List<TipoEncuesta>>(tipoEncuestas, HttpStatus.OK);
    }

    // CREAR NUEVA PREGUNTA
    @RequestMapping(value = "/crearTipoEncuesta", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearTipoEncuesta(@RequestBody TipoEncuesta tipoEncuesta, UriComponentsBuilder uriComponentsBuilder) {

        if (tipoEncuesta.getDescripcion().equals(null) || tipoEncuesta.getDescripcion().isEmpty()) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _tipoEncuestaServices.crearTipoEncuesta(tipoEncuesta);
        return new ResponseEntity<TipoEncuesta>(tipoEncuesta, HttpStatus.CREATED);
    }

    //ACTUALIZAR PREGUNTA EXISTENTE
    @RequestMapping(value = "/actualizarTipoEncuesta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actulizarTipoEncuesta(@PathVariable("id") Long id, @RequestBody TipoEncuesta tipoEncuesta) {

        TipoEncuesta tipoEncuestaUpdate = _tipoEncuestaServices.buscarPorId(id);
        if (tipoEncuestaUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        tipoEncuestaUpdate.setNombre(tipoEncuesta.getNombre());
        tipoEncuestaUpdate.setCarrera(tipoEncuesta.getCarrera());
        tipoEncuestaUpdate.setDescripcion(tipoEncuesta.getDescripcion());
        _tipoEncuestaServices.crearTipoEncuesta(tipoEncuestaUpdate);

        return new ResponseEntity<TipoEncuesta>(tipoEncuestaUpdate, HttpStatus.OK);
    }

    //BORRAR PREGUNTA EXISTENTE
    @RequestMapping(value = "/borrarTipoEncuesta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> borrarTipoEncuesta(@PathVariable("id") Long id, @RequestBody TipoEncuesta tipoEncuesta) {

        TipoEncuesta tipoEncuestaUpdate = _tipoEncuestaServices.buscarPorId(id);
        if (tipoEncuestaUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        _tipoEncuestaServices.eliminarTipoEncuesta(id);

        return new ResponseEntity("Se ha borrado con exito", HttpStatus.OK);
    }
}
