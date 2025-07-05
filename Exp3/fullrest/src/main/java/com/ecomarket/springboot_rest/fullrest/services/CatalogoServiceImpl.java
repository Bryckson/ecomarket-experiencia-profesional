package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;
import com.ecomarket.springboot_rest.fullrest.repositories.CatalogoRepositories;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoRepositories catalogoRepository;

    @Override
    @Transactional
    public Optional<Catalogo> delete(Catalogo unCatalogo) {
        Optional<Catalogo> catalogoOptional = catalogoRepository.findById(unCatalogo.getId());
        catalogoOptional.ifPresent(catalogoDb -> {
            catalogoRepository.delete(unCatalogo);
        });
        return catalogoOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Catalogo> findByAll() {
        return (List<Catalogo>) catalogoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Catalogo> findById(Long id) {
        return catalogoRepository.findById(id);
    }

    @Override
    @Transactional
    public Catalogo save(Catalogo unCatalogo) {
        return catalogoRepository.save(unCatalogo);
    }

    public CatalogoRepositories getCatalogoRepository() {
        return catalogoRepository;
    }

    public void setCatalogoRepository(CatalogoRepositories catalogoRepository) {
        this.catalogoRepository = catalogoRepository;
    }
}
