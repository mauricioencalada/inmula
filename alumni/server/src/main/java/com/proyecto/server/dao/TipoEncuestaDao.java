package com.proyecto.server.dao;

import com.proyecto.server.model.TipoEncuesta;

import java.util.List;

public interface TipoEncuestaDao {

    TipoEncuesta buscarPorId(Long id);

    TipoEncuesta crearTipoEncuesta(TipoEncuesta tipoEncuesta);

    TipoEncuesta actualizarTipoEncuesta(TipoEncuesta tipoEncuesta);

    void eliminarTipoEncuesta(Long id);

    List<TipoEncuesta> obtenerTodas();

}
