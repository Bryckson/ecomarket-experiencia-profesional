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
import com.ecomarket.springboot_rest.fullrest.services.CatalogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Catalogos", description="API de catálogos")
@RestController
@RequestMapping("api/Catalogos")
public class CatalogoRestController {

    @Autowired
    private CatalogoService catalogoService;

    @Operation(summary = "Obtener lista de Catálogos", description = "Devuelve todos los catálogos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de catálogos retornada correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class)))
    @GetMapping
    public List<Catalogo> mostrarCatalogos() {
        return catalogoService.findByAll();
    }

    @Operation(summary = "Obtener catálogo por ID", description = "Obtiene el detalle de un catálogo específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verCatalogo(@PathVariable Long id) {
        Optional<Catalogo> catalogoOptional = catalogoService.findById(id);
        if (catalogoOptional.isPresent()) {
            return ResponseEntity.ok(catalogoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo catálogo", description = "Crea un catálogo con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Catálogo creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class)))
    @PostMapping
    public ResponseEntity<Catalogo> crearCatalogo(@RequestBody Catalogo unCatalogo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogoService.save(unCatalogo));
    }

    @Operation(summary = "Modificar catálogo por ID", description = "Modificar información de un catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCatalogo(@PathVariable Long id, @RequestBody Catalogo unCatalogo) {
        Optional<Catalogo> catalogoOptional = catalogoService.findById(id);

        if (catalogoOptional.isPresent()) {
            Catalogo catalogoExistente = catalogoOptional.get();

            catalogoExistente.setNombre(unCatalogo.getNombre());
            catalogoExistente.setDescripcion(unCatalogo.getDescripcion());
            catalogoExistente.setCategoria(unCatalogo.getCategoria());
            catalogoExistente.setPrecio(unCatalogo.getPrecio());
            catalogoExistente.setSucursal(unCatalogo.getSucursal());
            catalogoExistente.setPromocion(unCatalogo.getPromocion());

            Catalogo catalogoModificado = catalogoService.save(catalogoExistente);
            return ResponseEntity.ok(catalogoModificado);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar catálogo por ID", description = "Elimina un catálogo específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCatalogo(@PathVariable Long id) {
        Catalogo unCatalogo = new Catalogo();
        unCatalogo.setId(id);
        Optional<Catalogo> catalogoOptional = catalogoService.delete(unCatalogo);
        if (catalogoOptional.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
