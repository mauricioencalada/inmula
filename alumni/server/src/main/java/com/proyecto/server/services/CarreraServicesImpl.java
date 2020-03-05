package com.proyecto.server.services;

import com.proyecto.server.dao.CarreraDao;
import com.proyecto.server.model.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("carreraServices")
@Transactional
public class CarreraServicesImpl implements CarreraServices {

    @Autowired
    private CarreraDao _carreraDao;

    @Override
    public Carrera buscarPorId(Long id) {
        return _carreraDao.buscarPorId(id);
    }

    @Override
    public Carrera crearCarrera(Carrera carrera) {
        return _carreraDao.crearCarrera(carrera);
    }

    @Override
    public Carrera actualizarCarrera(Carrera carrera) {
        return _carreraDao.crearCarrera(carrera);
    }

    @Override
    public void eliminarCarrera(Long id) {
        _carreraDao.eliminarCarrera(id);
    }

    @Override
    public List<Carrera> obtenerTodos() {
        return _carreraDao.obtenerTodos()
                ;
    }
}
