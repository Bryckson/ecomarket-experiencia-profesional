package com.ecomarket.springboot_rest.fullrest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ecomarket.springboot_rest.fullrest.entities.Inventario;

public interface InventarioRepository extends CrudRepository<Inventario, Long> {
}
