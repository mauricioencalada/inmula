package com.proyecto.server.dao;

import com.proyecto.server.model.EncuestaPregunta;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EncuestaPreguntaDaoImpl extends AbstractSession implements EncuestaPreguntaDao {
    @Override
    public EncuestaPregunta buscarPorId(Long id) {
        return getSession().get(EncuestaPregunta.class, id);
    }

    @Override
    public EncuestaPregunta crearEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
        getSession().persist(encuestaPregunta);
        return encuestaPregunta;
    }

    @Override
    public EncuestaPregunta actualizarEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
        getSession().update(encuestaPregunta);
        return encuestaPregunta;
    }

    @Override
    public void eliminarEncuestaPregunta(Long id) {
        EncuestaPregunta encuestaPregunta = buscarPorId(id);
        if (encuestaPregunta != null) {
            getSession().delete(encuestaPregunta);
        }
    }

    @Override
    public List<EncuestaPregunta> obtenerTodas() {
        return getSession().createQuery("from EncuestaPregunta order by respuesta.id asc").list();
    }
}
