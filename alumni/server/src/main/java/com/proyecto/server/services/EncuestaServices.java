package com.proyecto.server.services;

import com.proyecto.server.model.Encuesta;

import java.util.List;

public interface EncuestaServices {

    Encuesta buscarPorId(Long id);

    Encuesta crearEncuesta(Encuesta encuesta);

    Encuesta actualizarEncuesta(Encuesta encuesta);

    void eliminarEncuesta(Long id);

    List<Encuesta> obtenerTodas();
}
