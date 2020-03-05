package com.proyecto.server.dao;

import com.proyecto.server.model.Persona;

import java.util.List;

public interface PersonaDao {

    Persona buscarPorId(Long id);

    Persona verificarLogin(String cedula, String password);

    Persona buscarPorCedula(String cedula);

    Persona crearPersona(Persona persona);

    Persona actualizarPersona(Persona persona);

    void eliminarPersona(Long id);

    List<Persona> obtenerTodas();

}
