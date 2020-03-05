package com.proyecto.server.services;

import com.proyecto.server.dao.RolesDao;
import com.proyecto.server.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("rolesServices")
@Transactional
public class RolesServicesImpl implements RolesServices {

    @Autowired
    private RolesDao _rolesDao;

    @Override
    public List<Roles> obtenerTodas() {
        return _rolesDao.obtenerTodas();
    }
}
