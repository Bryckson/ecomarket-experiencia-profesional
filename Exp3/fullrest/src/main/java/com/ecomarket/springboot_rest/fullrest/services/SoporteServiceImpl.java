package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomarket.springboot_rest.fullrest.entities.Soporte;
import com.ecomarket.springboot_rest.fullrest.repositories.SoporteRepositories;

@Service
public class SoporteServiceImpl implements SoporteServices {

    @Autowired
    private SoporteRepositories soporterepository;

    @Override
    @Transactional
    public Optional<Soporte> delete(Soporte unSoporte) {
        Optional<Soporte> soporteOptional = soporterepository.findById(unSoporte.getId());
        soporteOptional.ifPresent(soporteDb->{
            soporterepository.delete(unSoporte);
        });
        return soporteOptional;
    }

    @Override
    @Transactional (readOnly = true)
    public List<Soporte> findByAll() {        
        return (List<Soporte>) soporterepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Soporte> findById(Long id) {        
        return soporterepository.findById(id);
    }

    @Override
    @Transactional
    public Soporte save(Soporte unSoporte) {        
        return soporterepository.save(unSoporte);
    }


    
}
