package com.proyecto.server.services;

import com.proyecto.server.model.Respuesta;

import java.util.List;

public interface RespuestaServices {

    Respuesta buscarPorId(Long id);

    Respuesta crearRespuesta(Respuesta respuesta);

    Respuesta actualizarRespuesta(Respuesta respuesta);

    void eliminarRespuesta(Long id);

    List<Respuesta> obtenerTodas();

}
