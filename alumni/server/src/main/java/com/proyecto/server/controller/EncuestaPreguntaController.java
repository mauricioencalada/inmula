package com.proyecto.server.controller;

import com.proyecto.server.model.EncuestaPregunta;
import com.proyecto.server.services.EncuestaPreguntaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/encuestaPregunta")
public class EncuestaPreguntaController {

    @Autowired
    private EncuestaPreguntaServices _encuestaPreguntaServices;

    //OBTENER LAS ENCUESTAPREGUNTA POR EL ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<EncuestaPregunta> getEncuestaPreguntaById(@PathVariable("id") Long id) {
        EncuestaPregunta encuestaPregunta = _encuestaPreguntaServices.buscarPorId(id);
        if (encuestaPregunta == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EncuestaPregunta>(encuestaPregunta, HttpStatus.OK);
    }

    //OBTENER TODOS LAS ENCUESTAPREGUNTA
    @RequestMapping(value = "/obtenerEncuestaPregunta", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<EncuestaPregunta>> obtenerEncuestaPreguntas() {
        List<EncuestaPregunta> encuestaPreguntas = new ArrayList<EncuestaPregunta>();

        encuestaPreguntas = _encuestaPreguntaServices.obtenerTodas();
        return new ResponseEntity<List<EncuestaPregunta>>(encuestaPreguntas, HttpStatus.OK);
    }


    //CREAR NUEVA ENCUESTA PREGUNTA
    @RequestMapping(value = "/crearEncuestaPregunta", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearEncuestaPregunta(@RequestBody EncuestaPregunta encuestaPregunta, UriComponentsBuilder uriComponentsBuilder) {

        Calendar c2 = new GregorianCalendar();
        int dia = c2.get(Calendar.DATE);
        int mes = c2.get(Calendar.MONTH);
        int annio = c2.get(Calendar.YEAR);
        encuestaPregunta.setFechaRespuesta(new GregorianCalendar(annio, mes, dia));

        if (encuestaPregunta.getEstado().equals(null)) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _encuestaPreguntaServices.crearEncuestaPregunta(encuestaPregunta);
        return new ResponseEntity<EncuestaPregunta>(encuestaPregunta, HttpStatus.CREATED);
    }


    //ACTUALIZAR ENCUESTA PREGUNTA
    @RequestMapping(value = "/actualizarEncuestaPregunta/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actualizarEncuestaPregunta(@PathVariable("id") Long id, @RequestBody EncuestaPregunta encuestaPregunta) {

        EncuestaPregunta encuestaPreguntaUpdate = _encuestaPreguntaServices.buscarPorId(id);
        if (encuestaPregunta == null) {
            return new ResponseEntity("No se encuentra encuesta pregunta", HttpStatus.NOT_FOUND);
        }
        Calendar c2 = new GregorianCalendar();
        int dia = c2.get(Calendar.DATE);
        int mes = c2.get(Calendar.MONTH);
        int annio = c2.get(Calendar.YEAR);

        encuestaPreguntaUpdate.setPreguntas(encuestaPregunta.getPreguntas());
        encuestaPreguntaUpdate.setFechaRespuesta(new GregorianCalendar(annio, mes, dia));
        encuestaPreguntaUpdate.setEncuesta(encuestaPregunta.getEncuesta());
        encuestaPreguntaUpdate.setRespuesta(encuestaPregunta.getRespuesta());
        encuestaPreguntaUpdate.setEstado(encuestaPregunta.getEstado());
        _encuestaPreguntaServices.actualizarEncuestaPregunta(encuestaPreguntaUpdate);

        return new ResponseEntity<EncuestaPregunta>(encuestaPreguntaUpdate, HttpStatus.OK);
    }

    //ELIMINAR ENCUESTA PREGUNTA
    @RequestMapping(value = "/borrarEncuestaPregunta/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<?> borrarEncuestaPregunta(@PathVariable("id") Long id, @RequestBody EncuestaPregunta encuestaPregunta) {

        EncuestaPregunta encuestaPreguntaUpdate = _encuestaPreguntaServices.buscarPorId(id);
        if (encuestaPreguntaUpdate == null) {
            return new ResponseEntity("No se encuentra blog", HttpStatus.NOT_FOUND);
        }

        _encuestaPreguntaServices.eliminarEncuestaPregunta(id);
        return new ResponseEntity("Se ha borrado con exito", HttpStatus.OK);
    }


}
