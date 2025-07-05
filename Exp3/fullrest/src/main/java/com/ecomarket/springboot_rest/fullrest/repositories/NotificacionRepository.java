package com.ecomarket.springboot_rest.fullrest.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;

public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {
    
}
