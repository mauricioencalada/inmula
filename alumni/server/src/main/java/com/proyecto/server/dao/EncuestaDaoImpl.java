package com.proyecto.server.dao;

import com.proyecto.server.model.Encuesta;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EncuestaDaoImpl extends AbstractSession implements EncuestaDao {

    @Override
    public Encuesta buscarPorId(Long id) {
        return getSession().get(Encuesta.class, id);
    }

    @Override
    public Encuesta crearEncuesta(Encuesta encuesta) {
        getSession().persist(encuesta);
        return encuesta;
    }

    @Override
    public Encuesta actualizarEncuesta(Encuesta encuesta) {
        getSession().update(encuesta);
        return encuesta;
    }

    @Override
    public void eliminarEncuesta(Long id) {
        Encuesta encuesta = buscarPorId(id);
        if (encuesta != null) {
            getSession().delete(encuesta);
        }
    }

    @Override
    public List<Encuesta> traerTodas() {
        return getSession().createQuery("from Encuesta").list();
    }
}
