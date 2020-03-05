package com.proyecto.server.dao;

import com.proyecto.server.model.EncuestaPregunta;

import java.util.List;

public interface EncuestaPreguntaDao {

    EncuestaPregunta buscarPorId(Long id);

    EncuestaPregunta crearEncuestaPregunta(EncuestaPregunta encuestaPregunta);

    EncuestaPregunta actualizarEncuestaPregunta(EncuestaPregunta encuestaPregunta);

    void eliminarEncuestaPregunta(Long id);

    List<EncuestaPregunta> obtenerTodas();

}
