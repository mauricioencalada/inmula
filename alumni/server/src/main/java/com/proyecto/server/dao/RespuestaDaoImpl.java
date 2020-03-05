package com.proyecto.server.dao;

import com.proyecto.server.model.Respuesta;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RespuestaDaoImpl extends AbstractSession implements RespuestaDao {

    @Override
    public Respuesta buscarPorId(Long id) {
        return getSession().get(Respuesta.class, id);

    }

    @Override
    public Respuesta crearPregunta(Respuesta respuesta) {
        getSession().persist(respuesta);
        return respuesta;
    }

    @Override
    public Respuesta actualizarRespuesta(Respuesta respuesta) {
        getSession().update(respuesta);
        return respuesta;
    }

    @Override
    public void eliminarRespuesta(Long id) {
        Respuesta respuesta = buscarPorId(id);
        if (respuesta != null) {
            getSession().delete(respuesta);
        }
    }

    @Override
    public List<Respuesta> obtenerTodas() {
        return getSession().createQuery("from Respuesta").list();
    }
}
