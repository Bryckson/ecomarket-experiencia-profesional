package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;

public interface CatalogoService {

    List<Catalogo> findByAll();

    Optional<Catalogo> findById(Long id);

    Catalogo save(Catalogo unCatalogo);

    Optional<Catalogo> delete(Catalogo unCatalogo);
}
