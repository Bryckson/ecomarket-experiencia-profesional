package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot_rest.fullrest.entities.Sucursal;

public interface SucursalServices {

    List<Sucursal> findByAll();

    Optional<Sucursal> findById(Long id);

    Sucursal save(Sucursal unaSucursal);

    Optional<Sucursal> delete(Sucursal unaSucursal);

}
