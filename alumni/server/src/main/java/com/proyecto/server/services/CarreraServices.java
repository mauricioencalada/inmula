package com.proyecto.server.services;

import com.proyecto.server.model.Carrera;

import java.util.List;

public interface CarreraServices {

    Carrera buscarPorId(Long id);

    Carrera crearCarrera(Carrera carrera);

    Carrera actualizarCarrera(Carrera carrera);

    void eliminarCarrera(Long id);

    List<Carrera> obtenerTodos();

}
