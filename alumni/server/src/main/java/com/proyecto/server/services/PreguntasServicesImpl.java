package com.proyecto.server.services;

import com.proyecto.server.dao.PreguntasDao;
import com.proyecto.server.model.Preguntas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("preguntasServices")
@Transactional
public class PreguntasServicesImpl implements PreguntasServices{

    @Autowired
    private PreguntasDao _preguntasDao;

    @Override
    public Preguntas buscarPorId(Long id) {
        return _preguntasDao.buscarPorId(id);
    }

    @Override
    public Preguntas actualizarPreguntas(Preguntas preguntas) {
        return _preguntasDao.actualizarPreguntas(preguntas);
    }

    @Override
    public Preguntas guardarPreguntas(Preguntas preguntas) {
        return _preguntasDao.guardarPreguntas(preguntas);
    }

    @Override
    public void eliminarPreguntas(Long id) {
        _preguntasDao.eliminarPreguntas(id);
    }

    @Override
    public List<Preguntas> obtenerTodas() {
        return _preguntasDao.obtenerTodas();
    }
}
