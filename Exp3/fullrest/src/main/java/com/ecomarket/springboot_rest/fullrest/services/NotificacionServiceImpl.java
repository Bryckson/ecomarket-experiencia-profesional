package com.ecomarket.springboot_rest.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;
import com.ecomarket.springboot_rest.fullrest.repositories.NotificacionRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository NotificacionRepository;

    @Override
    @Transactional
    public List<Notificacion> findByAll() {
        return (List<Notificacion>) NotificacionRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Notificacion> findById(Long id) {
        return NotificacionRepository.findById(id);
    }

    @Override
    @Transactional
    public Notificacion save(Notificacion Notificacion) {
        return NotificacionRepository.save(Notificacion);
    }

    @Override
    @Transactional
    public Optional<Notificacion> delete(Notificacion Notificacion) {
        Optional<Notificacion> NotificacionOptional = NotificacionRepository.findById(Notificacion.getId());
        NotificacionOptional.ifPresent(n -> NotificacionRepository.delete(n));
        return NotificacionOptional;
    }
}
