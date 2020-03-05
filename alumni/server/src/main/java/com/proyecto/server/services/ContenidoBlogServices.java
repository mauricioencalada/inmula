package com.proyecto.server.services;

import com.proyecto.server.model.ContenidoBlog;

import java.util.List;

public interface ContenidoBlogServices {

    ContenidoBlog buscarPorId(Long id);

    ContenidoBlog crearContenidoBlog(ContenidoBlog contenidoBlog);

    ContenidoBlog actualizarContenidoBlog(ContenidoBlog contenidoBlog);

    void eliminarContenidoBlog(Long id);

    List<ContenidoBlog> obtenerTodos();

}
