package com.proyecto.server.dao;

import com.proyecto.server.model.ContenidoBlog;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ContenidoBlogDaoImpl extends AbstractSession implements ContenidoBlogDao {

    @Override
    public ContenidoBlog buscarPorId(Long id) {
        return getSession().get(ContenidoBlog.class, id);
    }

    @Override
    public ContenidoBlog crearContenidoBlog(ContenidoBlog contenidoBlog) {
        getSession().persist(contenidoBlog);
        return contenidoBlog;
    }

    @Override
    public ContenidoBlog actualizarContenidoBlog(ContenidoBlog contenidoBlog) {
        getSession().update(contenidoBlog);
        return contenidoBlog;
    }

    @Override
    public void eliminarContenidoBlog(Long id) {
        ContenidoBlog contenidoBlog = buscarPorId(id);
        if (contenidoBlog != null) {
            getSession().delete(contenidoBlog);
        }
    }

    @Override
    public List<ContenidoBlog> obtenerTodos() {
        return getSession().createQuery("from ContenidoBlog").list();
    }
}
