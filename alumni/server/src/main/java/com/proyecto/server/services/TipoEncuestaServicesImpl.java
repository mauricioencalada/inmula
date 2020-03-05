package com.proyecto.server.services;

import com.proyecto.server.dao.TipoEncuestaDao;
import com.proyecto.server.model.TipoEncuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("tipoEncuestaServices")
@Transactional
public class TipoEncuestaServicesImpl implements TipoEncuestaServices {

    @Autowired
    private TipoEncuestaDao _tipoEncuestaDao;

    @Override
    public TipoEncuesta buscarPorId(Long id) {
        return _tipoEncuestaDao.buscarPorId(id);
    }

    @Override
    public TipoEncuesta crearTipoEncuesta(TipoEncuesta tipoEncuesta) {
        return _tipoEncuestaDao.crearTipoEncuesta(tipoEncuesta);
    }

    @Override
    public TipoEncuesta actualizarTipoEncuesta(TipoEncuesta tipoEncuesta) {
        return _tipoEncuestaDao.actualizarTipoEncuesta(tipoEncuesta);
    }

    @Override
    public void eliminarTipoEncuesta(Long id) {
        _tipoEncuestaDao.eliminarTipoEncuesta(id);
    }

    @Override
    public List<TipoEncuesta> obtenerTodas() {
        return _tipoEncuestaDao.obtenerTodas();
    }
}
