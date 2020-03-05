package com.proyecto.server.dao;

import com.proyecto.server.model.Carrera;

import java.util.List;

public interface CarreraDao {

    Carrera buscarPorId(Long id);

    Carrera crearCarrera(Carrera carrera);

    Carrera actualizarCarrera(Carrera carrera);

    void eliminarCarrera(Long id);

    List<Carrera> obtenerTodos();
}
