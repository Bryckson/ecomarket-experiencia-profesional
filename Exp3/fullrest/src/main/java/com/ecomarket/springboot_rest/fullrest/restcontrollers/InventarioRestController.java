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

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;
import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.services.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

    @Tag(name="Inventario", description="API de inventario")
    @RestController
    @RequestMapping("/api/inventario")
    public class InventarioRestController {

        @Autowired
        private InventarioService inventarioService;

        @Operation(summary = "Obtener lista de inventario", description = "Devuelve todos los inventarios disponibles")
        @ApiResponse(responseCode = "200", description = "Lista de inventario retornada correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class)))

        // Obtener todos los registros
        @GetMapping
        public List<Inventario> listarInventario() {
            return inventarioService.findByAll();
        }

        @Operation(summary = "Obtener inventario por ID", description = "Obtiene el detalle de un inventario específico")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventario encontrado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
            @ApiResponse(responseCode = "404", description = "inventario no encontrado")
        })

        // Obtener un inventario por ID
        @GetMapping("/{id}")
        public ResponseEntity<?> obtenerInventario(@PathVariable Long id) {
            Optional<Inventario> inventarioOptional = inventarioService.findById(id);
            if (inventarioOptional.isPresent()) {
                return ResponseEntity.ok(inventarioOptional.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventario no encontrado con ID: " + id);
        }

        
        @Operation(summary = "Crear un nuevo inventario", description = "Crea un inventario con los datos proporcionados")
        @ApiResponse(responseCode = "201", description = "inventario creado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class)))

        // Crear nuevo inventario
        @PostMapping
        public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
            Inventario nuevo = inventarioService.save(inventario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        }

        @Operation(summary = "Modificar inventario por ID", description = "Modificar información de un inventario")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventario modificado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
            @ApiResponse(responseCode = "404", description = "inventario no encontrado")
        })

        // Actualizar un inventario existente
        @PutMapping("/{id}")
        public ResponseEntity<?> actualizarInventario(@PathVariable Long id, @RequestBody Inventario datosNuevos) {
            Optional<Inventario> inventarioOptional = inventarioService.findById(id);
            if (inventarioOptional.isPresent()) {
                Inventario inventarioExistente = inventarioOptional.get();
                inventarioExistente.setNombreProducto(datosNuevos.getNombreProducto());
                inventarioExistente.setCategoria(datosNuevos.getCategoria());
                inventarioExistente.setCantidad(datosNuevos.getCantidad());
                inventarioExistente.setPrecioUnitario(datosNuevos.getPrecioUnitario());
                inventarioExistente.setActivo(datosNuevos.getActivo());
                Inventario actualizado = inventarioService.save(inventarioExistente);
                return ResponseEntity.ok(actualizado);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar. Inventario no encontrado con ID: " + id);
        }

        @Operation(summary = "Eliminar inventario por ID", description = "Elimina un inventario específico")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventario eliminado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
            @ApiResponse(responseCode = "404", description = "inventario no encontrado")
        })

        // Eliminar inventario por ID
        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminarInventario(@PathVariable Long id) {
            Optional<Inventario> inventarioOptional = inventarioService.findById(id);
            if (inventarioOptional.isPresent()) {
                inventarioService.delete(id);
                return ResponseEntity.ok().body("Inventario eliminado correctamente.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar. Inventario no encontrado con ID: " + id);
        }
    }
