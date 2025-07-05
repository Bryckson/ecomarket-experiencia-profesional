package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot_rest.fullrest.entities.Soporte;

public interface  SoporteServices {

    List<Soporte> findByAll();

    Optional<Soporte> findById(Long id);

    Soporte save(Soporte unSoporte);

    Optional<Soporte> delete (Soporte unSoporte);


}
