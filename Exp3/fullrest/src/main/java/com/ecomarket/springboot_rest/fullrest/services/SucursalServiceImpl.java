package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomarket.springboot_rest.fullrest.entities.Sucursal;
import com.ecomarket.springboot_rest.fullrest.repositories.SucursalRepositories;

@Service
public class SucursalServiceImpl implements SucursalServices {

    @Autowired
    private SucursalRepositories sucursalrepository;

    @Override
    @Transactional
    public Optional<Sucursal> delete(Sucursal unaSucursal) {
        Optional<Sucursal> sucursalOptional = sucursalrepository.findById(unaSucursal.getId());
        sucursalOptional.ifPresent(sucursalDb -> {
            sucursalrepository.delete(unaSucursal);
        });
        return sucursalOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findByAll() {        
        return (List<Sucursal>) sucursalrepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sucursal> findById(Long id) {        
        return sucursalrepository.findById(id);
    }

    @Override
    @Transactional
    public Sucursal save(Sucursal unaSucursal) {        
        return sucursalrepository.save(unaSucursal);
    }
}
