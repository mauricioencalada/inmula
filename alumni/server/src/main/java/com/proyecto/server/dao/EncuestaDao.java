package com.proyecto.server.dao;

import com.proyecto.server.model.Encuesta;

import java.util.List;

public interface EncuestaDao {

    Encuesta buscarPorId(Long id);

    Encuesta crearEncuesta(Encuesta encuesta);

    Encuesta actualizarEncuesta(Encuesta encuesta);

    void eliminarEncuesta(Long id);

    List<Encuesta> traerTodas();

}
