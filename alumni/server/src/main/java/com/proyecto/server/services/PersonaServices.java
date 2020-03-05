package com.proyecto.server.services;

import com.proyecto.server.model.Persona;

import java.util.List;

public interface PersonaServices {

    Persona buscarPorId(Long id);

    Persona verificarLogin(String personaBanner, String password);

    Persona buscarPorCedula(String cedula);

    Persona crearPersona(Persona persona);

    Persona actualizarPersona(Persona persona);

    void eliminarPersona(Long id);

    List<Persona> obtenerTodas();

}
