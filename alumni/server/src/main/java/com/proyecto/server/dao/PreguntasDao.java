package com.proyecto.server.dao;

import java.util.List;
import com.proyecto.server.model.Preguntas;

public interface PreguntasDao {

    Preguntas buscarPorId(Long id);

    Preguntas actualizarPreguntas(Preguntas preguntas);

    Preguntas guardarPreguntas(Preguntas preguntas);

    void eliminarPreguntas(Long id);

    List<Preguntas> obtenerTodas();

}
