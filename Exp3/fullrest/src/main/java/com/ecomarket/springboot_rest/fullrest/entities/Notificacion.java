package com.ecomarket.springboot_rest.fullrest.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private Long idUsuario;
    private String tipo; // tipo de notificacin: "sistema", "promocion", "recordatorio", etc.
    
    @Column(length = 1000)
    private String mensaje;

    private LocalDateTime fechaCreacion;
    
    private Boolean leido; // Si la notificación ya fue vista o no

    public Notificacion() {}

    public Notificacion(Long id, Long idUsuario, String tipo, String mensaje, LocalDateTime fechaCreacion, Boolean leido) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.leido = leido;
    }

    public Long getId() { return id; }

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

    public Boolean getLeido() {
         return leido; 
        }

    public void setLeido(Boolean leido) {
         this.leido = leido; 
        }

    @Override
    public String toString() {
        return "Notification [id=" + id + ", idUsuario=" + idUsuario + ", tipo=" + tipo + ", mensaje=" + mensaje
                + ", fechaCreacion=" + fechaCreacion + ", leido=" + leido + "]";
    }
}
