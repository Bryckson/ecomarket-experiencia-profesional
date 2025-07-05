package com.ecomarket.springboot_rest.fullrest.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.springboot_rest.fullrest.entities.Soporte;
import com.ecomarket.springboot_rest.fullrest.services.SoporteServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Soportes", description="API de soporte")
@RestController
@RequestMapping("api/Soportes")
public class SoporteRestController {

    @Autowired
    private SoporteServices soporteservice;

    
    @Operation(summary = "Obtener lista de Soportes", description = "Devuelve todos los soportes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de soporte retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Soporte.class)))

    @GetMapping
    public List<Soporte> mostrarSoportes(){
        return soporteservice.findByAll();
    }


    @Operation(summary = "Obtener soporte por ID", description = "Obtiene el detalle de un soporte específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "soporte encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Soporte.class))),
        @ApiResponse(responseCode = "404", description = "soporte no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verSoporte(@PathVariable Long id){
        Optional<Soporte> soporteOptional = soporteservice.findById(id);
        if (soporteOptional.isPresent()) {
            return ResponseEntity.ok(soporteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo soporte", description = "Crea un soporte con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "soporte creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Soporte.class)))

    @PostMapping
    public ResponseEntity<Soporte> crearSoporte(@RequestBody Soporte unSoporte){
        return ResponseEntity.status (HttpStatus.CREATED). body(soporteservice.save(unSoporte));
    }

    @Operation(summary = "Modificar soporte por ID", description = "Modificar información de un soporte")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "soporte modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Soporte.class))),
        @ApiResponse(responseCode = "404", description = "soporte no encontrado")
    })

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarSoporte(@PathVariable Long id, @RequestBody Soporte unSoporte) {
    Optional<Soporte> soporteOptional = soporteservice.findById(id);

    if (soporteOptional.isPresent()) {
        Soporte soporteExistente = soporteOptional.get();

        soporteExistente.setIdUsuario(unSoporte.getIdUsuario());
        soporteExistente.setTipo(unSoporte.getTipo());
        soporteExistente.setMensaje(unSoporte.getMensaje());
        soporteExistente.setFechaCreacion(unSoporte.getFechaCreacion());
        soporteExistente.setEstado(unSoporte.getEstado());
        soporteExistente.setRespuesta(unSoporte.getRespuesta());
        soporteExistente.setFechaRespuesta(unSoporte.getFechaRespuesta());
        soporteExistente.setActivo(unSoporte.getActivo());

        Soporte soporteModificado = soporteservice.save(soporteExistente);
        return ResponseEntity.ok(soporteModificado);
    }

    return ResponseEntity.notFound().build();

}
    @Operation(summary = "Eliminar soporte por ID", description = "Elimina un objeto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "soporte eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Soporte.class))),
        @ApiResponse(responseCode = "404", description = "soporte no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSoporte (@PathVariable Long id){
        Soporte unSoporte = new Soporte();
        unSoporte.setId(id);
        Optional <Soporte> soporteOptional = soporteservice.delete(unSoporte);
        if (soporteOptional.isPresent()){
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }    
}

