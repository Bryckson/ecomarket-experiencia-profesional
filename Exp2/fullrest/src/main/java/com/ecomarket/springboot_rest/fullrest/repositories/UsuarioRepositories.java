package com.ecomarket.springboot_rest.fullrest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ecomarket.springboot_rest.fullrest.entities.Usuario;

public interface UsuarioRepositories extends CrudRepository<Usuario,Long>{

}
