package com.ecomarket.springboot_rest.fullrest.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;
import com.ecomarket.springboot_rest.fullrest.services.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Notificaciones", description = "API de gestión de notificaciones")
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionRestController {

    @Autowired
    private NotificacionService notificacionService;

    // GET - Listar todas las notificaciones
    @Operation(summary = "Obtener lista de notificaciones", description = "Devuelve todas las notificaciones disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de notificaciones retornada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class)))
    @GetMapping
    public List<Notificacion> mostrarNotificaciones() {
        return notificacionService.findByAll();
    }

    // GET - Obtener notificación por ID
    @Operation(summary = "Obtener notificación por ID", description = "Obtiene el detalle de una notificación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verNotificacion(@PathVariable Long id) {
        Optional<Notificacion> notificacionOptional = notificacionService.findById(id);
        if (notificacionOptional.isPresent()) {
            return ResponseEntity.ok(notificacionOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // POST - Crear nueva notificación
    @Operation(summary = "Crear nueva notificación", description = "Crea una notificación con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Notificación creada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class)))
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody Notificacion notificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.save(notificacion));
    }

    // PUT - Modificar notificación por ID
    @Operation(summary = "Modificar notificación por ID", description = "Modifica la información de una notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación modificada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarNotificacion(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        Optional<Notificacion> notificacionOptional = notificacionService.findById(id);
        if (notificacionOptional.isPresent()) {
            Notificacion notificacionExistente = notificacionOptional.get();

            // Actualizar campos
            notificacionExistente.setIdUsuario(notificacion.getIdUsuario());
            notificacionExistente.setTipo(notificacion.getTipo());
            notificacionExistente.setMensaje(notificacion.getMensaje());
            notificacionExistente.setFechaCreacion(notificacion.getFechaCreacion());
            notificacionExistente.setLeido(notificacion.getLeido());

            Notificacion notificacionModificada = notificacionService.save(notificacionExistente);
            return ResponseEntity.ok(notificacionModificada);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE - Eliminar notificación por ID
    @Operation(summary = "Eliminar notificación por ID", description = "Elimina una notificación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación eliminada"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNotificacion(@PathVariable Long id) {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(id);
        Optional<Notificacion> notificacionOptional = notificacionService.delete(notificacion);
        if (notificacionOptional.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
