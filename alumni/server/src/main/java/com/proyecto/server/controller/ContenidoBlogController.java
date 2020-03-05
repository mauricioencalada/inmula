package com.proyecto.server.controller;

import com.proyecto.server.model.ContenidoBlog;
import com.proyecto.server.services.ContenidoBlogServices;
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
@RequestMapping(value = "/contenidoBlog")
public class ContenidoBlogController {


    @Autowired
    private ContenidoBlogServices _contenidoBlogServices;

    //OBTENER LOS BLOG POR EL ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<ContenidoBlog> getContenidoBlogById(@PathVariable("id") Long id) {
        ContenidoBlog contenidoBlog = _contenidoBlogServices.buscarPorId(id);
        if (contenidoBlog == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContenidoBlog>(contenidoBlog, HttpStatus.OK);
    }

    //OBTENER TODOS LOS BLOG
    @RequestMapping(value = "/obtenerContenidoBlog", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<ContenidoBlog>> obtenerContenidoBlogs() {
        List<ContenidoBlog> contenidoBlogs = new ArrayList<ContenidoBlog>();

        contenidoBlogs = _contenidoBlogServices.obtenerTodos();
        return new ResponseEntity<List<ContenidoBlog>>(contenidoBlogs, HttpStatus.OK);
    }


    //CREAR NUEVO BLOG
    @RequestMapping(value = "/crearContenidoBlog", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearContenidoBlog(@RequestBody ContenidoBlog contenidoBlog, UriComponentsBuilder uriComponentsBuilder) {

        if (contenidoBlog.getContenido().equals(null) || contenidoBlog.getContenido().isEmpty()) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }
        _contenidoBlogServices.crearContenidoBlog(contenidoBlog);
        return new ResponseEntity<ContenidoBlog>(contenidoBlog, HttpStatus.CREATED);
    }


    //ACTUALIZAR ContenidoBlog
    @RequestMapping(value = "/actualizarContenidoBlog/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actualizarContenidoBlog(@PathVariable("id") Long id, @RequestBody ContenidoBlog contenidoBlog) {

        ContenidoBlog contenidoBlogUpdate = _contenidoBlogServices.buscarPorId(id);
        if (contenidoBlogUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        contenidoBlogUpdate.setContenido(contenidoBlog.getContenido());
        contenidoBlogUpdate.setTipoBlog(contenidoBlog.getTipoBlog());
        contenidoBlogUpdate.setFechaFin(contenidoBlog.getFechaFin());
        contenidoBlogUpdate.setFechaInicio(contenidoBlog.getFechaInicio());
        contenidoBlogUpdate.setSerial(contenidoBlog.getSerial());
        _contenidoBlogServices.actualizarContenidoBlog(contenidoBlogUpdate);
        return new ResponseEntity<ContenidoBlog>(contenidoBlogUpdate, HttpStatus.OK);
    }

    //ELIMINAR CONTENIDOBLOG
    @RequestMapping(value = "/borrarContenidoBlog/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<?> borrarContenidoBlog(@PathVariable("id") Long id, @RequestBody ContenidoBlog contenidoBlog) {

        ContenidoBlog contenidoBlogUpdate = _contenidoBlogServices.buscarPorId(id);
        if (contenidoBlogUpdate == null) {
            return new ResponseEntity("No se encuentra blog", HttpStatus.NOT_FOUND);
        }

        _contenidoBlogServices.eliminarContenidoBlog(id);
        return new ResponseEntity("Se ha borrado con exito", HttpStatus.OK);
    }


}
