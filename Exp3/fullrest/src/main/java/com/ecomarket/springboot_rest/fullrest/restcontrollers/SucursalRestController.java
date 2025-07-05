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

import com.ecomarket.springboot_rest.fullrest.entities.Sucursal;
import com.ecomarket.springboot_rest.fullrest.services.SucursalServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Sucursales", description="API de sucursales")
@RestController
@RequestMapping("api/Sucursales")
public class SucursalRestController {

    @Autowired
    private SucursalServices sucursalservice;

    @Operation(summary = "Obtener lista de Sucursales", description = "Devuelve todas las sucursales disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Sucursal.class)))
    @GetMapping
    public List<Sucursal> mostrarSucursales(){
        return sucursalservice.findByAll();
    }

    @Operation(summary = "Obtener sucursal por ID", description = "Obtiene el detalle de una sucursal específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal encontrada",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verSucursal(@PathVariable Long id){
        Optional<Sucursal> sucursalOptional = sucursalservice.findById(id);
        if (sucursalOptional.isPresent()) {
            return ResponseEntity.ok(sucursalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva sucursal", description = "Crea una sucursal con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Sucursal creada correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody Sucursal unaSucursal){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalservice.save(unaSucursal));
    }

    @Operation(summary = "Modificar sucursal por ID", description = "Modificar información de una sucursal")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal modificada",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarSucursal(@PathVariable Long id, @RequestBody Sucursal unaSucursal) {
        Optional<Sucursal> sucursalOptional = sucursalservice.findById(id);

        if (sucursalOptional.isPresent()) {
            Sucursal sucursalExistente = sucursalOptional.get();

            sucursalExistente.setNombre(unaSucursal.getNombre());
            sucursalExistente.setDireccion(unaSucursal.getDireccion());
            sucursalExistente.setCiudad(unaSucursal.getCiudad());
            sucursalExistente.setRegion(unaSucursal.getRegion());
            sucursalExistente.setZonaCobertura(unaSucursal.getZonaCobertura());
            sucursalExistente.setActiva(unaSucursal.getActiva());

            Sucursal sucursalModificada = sucursalservice.save(sucursalExistente);
            return ResponseEntity.ok(sucursalModificada);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar sucursal por ID", description = "Elimina una sucursal específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal eliminada",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSucursal(@PathVariable Long id){
        Sucursal unaSucursal = new Sucursal();
        unaSucursal.setId(id);
        Optional<Sucursal> sucursalOptional = sucursalservice.delete(unaSucursal);
        if (sucursalOptional.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
