package com.proyecto.server.services;

import com.proyecto.server.dao.ContenidoBlogDao;
import com.proyecto.server.model.ContenidoBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("contenidoBlogServices")
@Transactional
public class ContenidoBlogServicesImpl implements ContenidoBlogServices {

    @Autowired
    private ContenidoBlogDao _contenidoBlogDao;

    @Override
    public ContenidoBlog buscarPorId(Long id) {
        return _contenidoBlogDao.buscarPorId(id);
    }

    @Override
    public ContenidoBlog crearContenidoBlog(ContenidoBlog contenidoBlog) {
        return _contenidoBlogDao.crearContenidoBlog(contenidoBlog);
    }

    @Override
    public ContenidoBlog actualizarContenidoBlog(ContenidoBlog contenidoBlog) {
        return _contenidoBlogDao.actualizarContenidoBlog(contenidoBlog);
    }

    @Override
    public void eliminarContenidoBlog(Long id) {
        _contenidoBlogDao.eliminarContenidoBlog(id);
    }

    @Override
    public List<ContenidoBlog> obtenerTodos() {
        return _contenidoBlogDao.obtenerTodos();
    }
}
