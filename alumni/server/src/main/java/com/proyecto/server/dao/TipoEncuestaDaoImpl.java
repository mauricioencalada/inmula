package com.proyecto.server.dao;

import com.proyecto.server.model.TipoEncuesta;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TipoEncuestaDaoImpl extends AbstractSession implements TipoEncuestaDao {
    @Override
    public TipoEncuesta buscarPorId(Long id) {
        return getSession().get(TipoEncuesta.class, id);
    }

    @Override
    public TipoEncuesta crearTipoEncuesta(TipoEncuesta tipoEncuesta) {
        getSession().persist(tipoEncuesta);
        return tipoEncuesta;
    }

    @Override
    public TipoEncuesta actualizarTipoEncuesta(TipoEncuesta tipoEncuesta) {
        getSession().update(tipoEncuesta);
        return tipoEncuesta;
    }

    @Override
    public void eliminarTipoEncuesta(Long id) {
        TipoEncuesta tipoEncuesta = buscarPorId(id);
        if (tipoEncuesta != null) {
            getSession().delete(tipoEncuesta);
        }
    }

    @Override
    public List<TipoEncuesta> obtenerTodas() {
        return getSession().createQuery("from TipoEncuesta").list();
    }
}
