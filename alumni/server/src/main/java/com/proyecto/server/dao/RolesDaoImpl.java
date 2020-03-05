package com.proyecto.server.dao;

import com.proyecto.server.model.Respuesta;
import com.proyecto.server.model.Roles;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RolesDaoImpl extends AbstractSession implements RolesDao{

    @Override
    public List<Roles> obtenerTodas() {
        return getSession().createQuery("from Roles").list();
    }
}
