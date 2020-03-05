package com.proyecto.server.dao;

import com.proyecto.server.model.Persona;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import oracle.jdbc.OracleConnection;
import java.sql.Clob;
import  oracle.sql.CLOB;
import java.util.List;

@Repository
@Transactional
public class PersonaDaoImpl extends AbstractSession implements PersonaDao {

    @Override
    public Persona buscarPorId(Long id) {
        return getSession().get(Persona.class, id);
    }

    //to_char(password)

    @Override
    public Persona verificarLogin(String personaBanner, String password) {
        return (Persona) getSession().createQuery(
                "from Persona where personaBanner = :personaBanner and to_char(password) = :password")
                .setParameter("personaBanner", personaBanner).setParameter("password", password).uniqueResult();
    }

    @Override
    public Persona buscarPorCedula(String cedula) {
        return (Persona) getSession().createQuery(
                "from Persona where personaBanner = :personaBanner")
                .setParameter("personaBanner", cedula).uniqueResult();
    }

    @Override
    public Persona crearPersona(Persona persona) {
        getSession().persist(persona);
        return persona;
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        getSession().update(persona);
        return persona;
    }

    @Override
    public void eliminarPersona(Long id) {
        Persona persona = buscarPorId(id);
        if (persona != null) {
            getSession().delete(persona);
        }
    }

    @Override
    public List<Persona> obtenerTodas() {
        return getSession().createQuery("from Persona").list();
    }
}
