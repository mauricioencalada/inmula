package com.proyecto.server.dao;

import com.proyecto.server.model.Respuesta;

import java.util.List;

public interface RespuestaDao {

    Respuesta buscarPorId(Long id);

    Respuesta crearPregunta(Respuesta respuesta);

    Respuesta actualizarRespuesta(Respuesta respuesta);

    void eliminarRespuesta(Long id);

    List<Respuesta> obtenerTodas();

}
