package com.proyecto.server.services;

import com.proyecto.server.model.Preguntas;

import java.util.List;

public interface PreguntasServices {

    Preguntas buscarPorId(Long id);

    Preguntas actualizarPreguntas(Preguntas preguntas);

    Preguntas guardarPreguntas(Preguntas preguntas);

    void eliminarPreguntas(Long id);

    List<Preguntas> obtenerTodas();

}
