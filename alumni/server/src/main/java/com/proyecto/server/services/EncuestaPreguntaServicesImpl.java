package com.proyecto.server.services;

import com.proyecto.server.dao.EncuestaPreguntaDao;
import com.proyecto.server.model.EncuestaPregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("encuestaPreguntaService")
@Transactional
public class EncuestaPreguntaServicesImpl implements EncuestaPreguntaServices {

    @Autowired
    private EncuestaPreguntaDao _encuestaPreguntaDao;

    @Override
    public EncuestaPregunta buscarPorId(Long id) {
        return _encuestaPreguntaDao.buscarPorId(id);
    }

    @Override
    public EncuestaPregunta crearEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
        return _encuestaPreguntaDao.crearEncuestaPregunta(encuestaPregunta);
    }

    @Override
    public EncuestaPregunta actualizarEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
        return _encuestaPreguntaDao.actualizarEncuestaPregunta(encuestaPregunta);
    }

    @Override
    public void eliminarEncuestaPregunta(Long id) {
        _encuestaPreguntaDao.eliminarEncuestaPregunta(id);
    }

    @Override
    public List<EncuestaPregunta> obtenerTodas() {
        return _encuestaPreguntaDao.obtenerTodas();
    }
}
