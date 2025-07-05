package com.ecomarket.springboot_rest.fullrest.services;

import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findByAll() {
        return (List<Inventario>) inventarioRepository.findAll();
    }

    @Override
    public Optional<Inventario> findById(Long id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public void delete(Long id) {
        inventarioRepository.deleteById(id);
    }
}
