package com.proyecto.server.controller;


import com.proyecto.server.model.Persona;
import com.proyecto.server.model.Roles;
import com.proyecto.server.services.PersonaServices;
import com.proyecto.server.services.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/persona")
public class PersonaController {

    @Autowired
    private RolesServices _rolesServices;

    @Autowired
    private PersonaServices _personaServices;

    //OBTENER PERSONA POR ID
    @RequestMapping(value = "/obtenerId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Persona> getPersonaById(@PathVariable("id") Long id) {
        Persona persona = _personaServices.buscarPorId(id);
        if (persona == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    //OBTENER TODAS LAS PREGUNTAS
    @RequestMapping(value = "/roles", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Roles>> obtenerPreguntas() {
        List<Roles> roles = new ArrayList<Roles>();

        roles = _rolesServices.obtenerTodas();
        return new ResponseEntity<List<Roles>>(roles, HttpStatus.OK);
    }

    //OBTENER PERSONA POR LOGIN
    @RequestMapping(value = "/auth", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity <Persona> loginPerson(@RequestBody Persona persona, UriComponentsBuilder uriComponentsBuilder) {
        persona.setPassword(getMD5(persona.getPassword()));
        Persona persona1 = _personaServices.verificarLogin(persona.getPersonaBanner(),  persona.getPassword());
        if (persona1 == null) {
            return new ResponseEntity("NO ENCONTRADO aqui salta ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity <Persona> (persona1, HttpStatus.OK);
    }

    //OBTENER TODAS LAS PERSONA
    @RequestMapping(value = "/obtenerPersona", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Persona>> obtenerPersonas() {
        List<Persona> personas = new ArrayList<Persona>();

        personas = _personaServices.obtenerTodas();
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

    // CREAR NUEVA PERSONAS
    @RequestMapping(value = "/crearPersona", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> crearPersona(@RequestBody Persona persona, UriComponentsBuilder uriComponentsBuilder) {

        if (_personaServices.buscarPorCedula(persona.getPersonaBanner()) != null) {
            return new ResponseEntity("Ya existe el usuario", HttpStatus.CONFLICT);
        }

        if (persona.getPersonaBanner().equals(null)) {
            return new ResponseEntity("Faltan Datos Necesarios", HttpStatus.CONFLICT);
        }

        persona.setPassword(getMD5(persona.getPassword()));
        _personaServices.crearPersona(persona);
        return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
    }

    //ACTUALIZAR PERSONA EXISTENTE
    @RequestMapping(value = "/actualizarPersona/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> actulizarPersona(@PathVariable("id") Long id, @RequestBody Persona persona) {

        Persona personaUpdate = _personaServices.buscarPorId(id);
        if (personaUpdate == null) {
            return new ResponseEntity("No se encuentra persona", HttpStatus.NOT_FOUND);
        }

        personaUpdate.setPassword(persona.getPassword());
        personaUpdate.setRoles(persona.getRoles());
        _personaServices.actualizarPersona(personaUpdate);

        return new ResponseEntity<Persona>(personaUpdate, HttpStatus.OK);
    }

    //BORRAR PERSONA EXISTENTE
    @RequestMapping(value = "/borrarPersona/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<?> borrarPersona(@PathVariable("id") Long id, @RequestBody Persona persona) {

        Persona personaUpdate = _personaServices.buscarPorId(id);
        if (personaUpdate == null) {
            return new ResponseEntity("No se encuentra expositor", HttpStatus.NOT_FOUND);
        }

        _personaServices.eliminarPersona(id);

        return new ResponseEntity("Se ha borrado con exito", HttpStatus.OK);
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
