package com.ecomarket.springboot_rest.fullrest.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Soporte {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private String tipo; // consulta, reclamo, suferencia, etc.

    @Column(length = 1000)
    private String mensaje;

    private LocalDateTime fechaCreacion;
    private String estado;  // abierto, en_progreso, resuelto, cerrado

    @Column(length = 1000)
    private String respuesta; 
    
    private LocalDateTime fechaRespuesta;
    private Boolean activo;

    public Soporte() {
    }

    public Soporte(Boolean activo, String estado, LocalDateTime fechaCreacion, LocalDateTime fechaRespuesta, Long id, Long idUsuario, String mensaje, String respuesta, String tipo) {
        this.activo = activo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaRespuesta = fechaRespuesta;
        this.id = id;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Soporte [id=" + id + ", idUsuario=" + idUsuario + ", tipo=" + tipo + ", mensaje=" + mensaje
                + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado + ", respuesta=" + respuesta
                + ", fechaRespuesta=" + fechaRespuesta + ", activo=" + activo + "]";
    }







}
