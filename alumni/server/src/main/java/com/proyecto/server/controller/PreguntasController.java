package com.proyecto.server.controller;

import com.proyecto.server.model.Preguntas;
import com.proyecto.server.model.Roles;
import com.proyecto.server.services.PreguntasServices;
import com.proyecto.server.services.RolesServices;
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
@RequestMapping(value = "/preguntas")
public class PreguntasController {

    @Autowired
    private PreguntasServices _preguntasServices;



    //OBTENER PREGUNTAS POR ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Preguntas> getPreguntasById(@PathVariable("id") Long id) {
        Preguntas preguntas = _preguntasServices.buscarPorId(id);
        if (preguntas == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Preguntas>(preguntas, HttpStatus.OK);
    }

    //OBTENER TODAS LAS PREGUNTAS
    @RequestMapping(value = "/obtenerPreguntas", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Preguntas>> obtenerPreguntas() {
        List<Preguntas> preguntas = new ArrayList<Preguntas>();

        preguntas = _preguntasServices.obtenerTodas();
        return new ResponseEntity<List<Preguntas>>(preguntas, HttpStatus.OK);
    }

    // CREAR NUEVA PREGUNTA
    @RequestMapping(value = "/crearPregunta", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearPregunta(@RequestBody Preguntas preguntas, UriComponentsBuilder uriComponentsBuilder) {

        if (preguntas.getDescripcion().equals(null) || preguntas.getDescripcion().isEmpty()) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _preguntasServices.guardarPreguntas(preguntas);
        return new ResponseEntity<Preguntas>(preguntas, HttpStatus.CREATED);
    }

    //ACTUALIZAR PREGUNTA EXISTENTE
    @RequestMapping(value = "/actualizarPregunta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actulizarPreguntas(@PathVariable("id") Long id, @RequestBody Preguntas preguntas) {

        Preguntas preguntaUpdate = _preguntasServices.buscarPorId(id);
        if (preguntaUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        preguntaUpdate.setDescripcion(preguntas.getDescripcion());
        preguntaUpdate.setTipoPregunta(preguntas.getTipoPregunta());
        _preguntasServices.actualizarPreguntas(preguntaUpdate);

        return new ResponseEntity<Preguntas>(preguntaUpdate, HttpStatus.OK);
    }

    //BORRAR PREGUNTA EXISTENTE
    @RequestMapping(value = "/borrarPregunta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> borrarExpositores(@PathVariable("id") Long id, @RequestBody Preguntas preguntas) {

        Preguntas preguntasUpdate = _preguntasServices.buscarPorId(id);
        if (preguntasUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        _preguntasServices.eliminarPreguntas(id);

        return new ResponseEntity("Se ha borrado con exito", HttpStatus.OK);
    }
}
