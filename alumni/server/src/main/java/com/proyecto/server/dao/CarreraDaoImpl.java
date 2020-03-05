package com.proyecto.server.dao;

import com.proyecto.server.model.Carrera;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CarreraDaoImpl extends AbstractSession implements CarreraDao {

    @Override
    public Carrera buscarPorId(Long id) {
        return getSession().get(Carrera.class, id);
    }

    @Override
    public Carrera crearCarrera(Carrera carrera) {
        getSession().persist(carrera);
        return carrera;
    }

    @Override
    public Carrera actualizarCarrera(Carrera carrera) {
        getSession().update(carrera);
        return carrera;
    }

    @Override
    public void eliminarCarrera(Long id) {
        Carrera carrera = buscarPorId(id);
        if (carrera != null) {
            getSession().delete(carrera);
        }
    }

    @Override
    public List<Carrera> obtenerTodos() {
        return getSession().createQuery("from Carrera").list();
    }
}
