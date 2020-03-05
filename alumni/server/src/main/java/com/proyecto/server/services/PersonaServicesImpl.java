package com.proyecto.server.services;

import com.proyecto.server.dao.PersonaDao;
import com.proyecto.server.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("personaServices")
@Transactional
public class PersonaServicesImpl implements PersonaServices {

    @Autowired
    private PersonaDao _personaDao;

    @Override
    public Persona buscarPorId(Long id) {
        return _personaDao.buscarPorId(id);
    }

    @Override
    public Persona verificarLogin(String personaBanner, String password) {
        return _personaDao.verificarLogin(personaBanner, password);
    }

    @Override
    public Persona buscarPorCedula(String cedula) {
        return _personaDao.buscarPorCedula(cedula);
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return _personaDao.crearPersona(persona);
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        return _personaDao.actualizarPersona(persona);
    }

    @Override
    public void eliminarPersona(Long id) {
        _personaDao.eliminarPersona(id);
    }

    @Override
    public List<Persona> obtenerTodas() {
        return _personaDao.obtenerTodas();
    }
}
