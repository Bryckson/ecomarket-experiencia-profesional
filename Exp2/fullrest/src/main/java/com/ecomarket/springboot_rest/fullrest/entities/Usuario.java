package com.ecomarket.springboot_rest.fullrest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String passwordHash;
    private String rol;
    private Boolean activo = true;


    
    public Usuario() {
    }



    public Usuario(Long id, String nombre, String email, String passwordHash, String rol, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = activo;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPasswordHash() {
        return passwordHash;
    }



    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }



    public String getRol() {
        return rol;
    }



    public void setRol(String rol) {
        this.rol = rol;
    }



    public Boolean getActivo() {
        return activo;
    }



    public void setActivo(Boolean activo) {
        this.activo = activo;
    }



    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", passwordHash=" + passwordHash
                + ", rol=" + rol + ", activo=" + activo + "]";
    }
    

    

}
