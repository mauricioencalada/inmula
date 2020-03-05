package com.proyecto.server.controller;

import com.proyecto.server.model.Respuesta;
import com.proyecto.server.services.RespuestaServices;
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
@RequestMapping(value = "/respuestas")
public class RespuestasController {

    @Autowired
    private RespuestaServices _respuestaServices;

    //OBTENER LAS RESPUESTA POR EL ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Respuesta> getRespuestaById(@PathVariable("id") Long id) {
        Respuesta respuesta = _respuestaServices.buscarPorId(id);
        if (respuesta == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
    }

    //OBTENER TODAS LAS RESPUESTAS
    @RequestMapping(value = "/obtenerRespuestas", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Respuesta>> obtenerRespuestas() {
        List<Respuesta> respuestas = new ArrayList<Respuesta>();

        respuestas = _respuestaServices.obtenerTodas();
        return new ResponseEntity<List<Respuesta>>(respuestas, HttpStatus.OK);
    }


    //CREAR NUEVA RESPUESTA
    @RequestMapping(value = "/crearRespuesta", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearRespuesta(@RequestBody Respuesta respuesta, UriComponentsBuilder uriComponentsBuilder) {

        if (respuesta.getPersona().getId().equals(null)) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _respuestaServices.crearRespuesta(respuesta);
        return new ResponseEntity<Respuesta>(respuesta, HttpStatus.CREATED);
    }


    //ACTUALIZAR RESPUESTA
    @RequestMapping(value = "/actualizarRespuesta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actualizarRespuesta(@PathVariable("id") Long id, @RequestBody Respuesta respuesta) {

        Respuesta respuestaUpdate = _respuestaServices.buscarPorId(id);
        if (respuestaUpdate == null) {
            return new ResponseEntity("No se encuentra respuesta", HttpStatus.NOT_FOUND);
        }
        respuestaUpdate.setRespuesta(respuesta.getRespuesta());
        respuestaUpdate.setDescripcion(respuesta.getDescripcion());
        _respuestaServices.actualizarRespuesta(respuestaUpdate);

        return new ResponseEntity<Respuesta>(respuestaUpdate, HttpStatus.OK);
    }

    //ELIMINAR RESPUESTA
    @RequestMapping(value = "/borrarRespuesta/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<?> borrarRespuesta(@PathVariable("id") Long id, @RequestBody Respuesta respuesta) {

        Respuesta respuestaUpdate = _respuestaServices.buscarPorId(id);
        if (respuestaUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        _respuestaServices.eliminarRespuesta(id);
        return new ResponseEntity("Se ha borrado con exito", HttpStatus.OK);
    }

}
