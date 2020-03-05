package com.proyecto.server.dao;


import com.proyecto.server.model.Preguntas;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PreguntasDaoImpl extends AbstractSession implements PreguntasDao {


    @Override
    public Preguntas buscarPorId(Long id) {
        return getSession().get(Preguntas.class, id);
    }

    @Override
    public Preguntas actualizarPreguntas(Preguntas preguntas) {
        getSession().update(preguntas);
        return preguntas;
    }

    @Override
    public Preguntas guardarPreguntas(Preguntas preguntas) {
        getSession().persist(preguntas);
        return preguntas;
    }

    @Override
    public void eliminarPreguntas(Long id) {
        Preguntas preguntas = buscarPorId(id);
        if (preguntas != null) {
            getSession().delete(preguntas);
        }
    }

    @Override
    public List<Preguntas> obtenerTodas() {
        return getSession().createQuery("from Preguntas").list();
    }
}
