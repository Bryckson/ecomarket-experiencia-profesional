package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;

public interface NotificacionService {
    
    public List<Notificacion> findByAll();

    public Optional<Notificacion> findById(Long id);

    public Notificacion save(Notificacion Notificacion);

    public Optional<Notificacion> delete(Notificacion Notificacion);

}
