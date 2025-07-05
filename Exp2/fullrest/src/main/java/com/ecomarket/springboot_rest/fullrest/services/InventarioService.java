package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot_rest.fullrest.entities.Inventario;

public interface InventarioService {
    List<Inventario> findByAll();
    Optional<Inventario> findById(Long id);
    Inventario save(Inventario inventario);
    void delete(Long id);
}
