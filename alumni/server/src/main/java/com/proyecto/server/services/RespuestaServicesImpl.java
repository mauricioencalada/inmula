package com.proyecto.server.services;

import com.proyecto.server.dao.RespuestaDao;
import com.proyecto.server.model.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("respuestaServices")
@Transactional
public class RespuestaServicesImpl implements RespuestaServices {

    @Autowired
    private RespuestaDao _respuestaDao;

    @Override
    public Respuesta buscarPorId(Long id) {
        return _respuestaDao.buscarPorId(id);
    }

    @Override
    public Respuesta crearRespuesta(Respuesta respuesta) {
        return _respuestaDao.crearPregunta(respuesta);
    }

    @Override
    public Respuesta actualizarRespuesta(Respuesta respuesta) {
        return _respuestaDao.actualizarRespuesta(respuesta);
    }

    @Override
    public void eliminarRespuesta(Long id) {
        _respuestaDao.eliminarRespuesta(id);
    }

    @Override
    public List<Respuesta> obtenerTodas() {
        return _respuestaDao.obtenerTodas();
    }
}
