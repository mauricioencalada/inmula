package com.proyecto.server.services;

import com.proyecto.server.dao.EncuestaDao;
import com.proyecto.server.model.Encuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("encuestaService")
@Transactional
public class EncuestaServicesImpl implements EncuestaServices {

    @Autowired
    private EncuestaDao _encuestaDao;

    @Override
    public Encuesta buscarPorId(Long id) {
        return _encuestaDao.buscarPorId(id);
    }

    @Override
    public Encuesta crearEncuesta(Encuesta encuesta) {
        return _encuestaDao.crearEncuesta(encuesta);
    }

    @Override
    public Encuesta actualizarEncuesta(Encuesta encuesta) {
        return _encuestaDao.actualizarEncuesta(encuesta);
    }

    @Override
    public void eliminarEncuesta(Long id) {
        _encuestaDao.eliminarEncuesta(id);
    }

    @Override
    public List<Encuesta> obtenerTodas() {
        return _encuestaDao.traerTodas();
    }
}
