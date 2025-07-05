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

@RestController
@RequestMapping("api/catalogos")
public class CatalogoRestController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public List<Catalogo> mostrarCatalogos() {
        return catalogoService.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verCatalogo(@PathVariable Long id) {
        Optional<Catalogo> catalogoOptional = catalogoService.findById(id);
        if (catalogoOptional.isPresent()) {
            return ResponseEntity.ok(catalogoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Catalogo> crearCatalogo(@RequestBody Catalogo unCatalogo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogoService.save(unCatalogo));
    }

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

  @DeleteMapping("/{id}")
public ResponseEntity<?> eliminarCatalogo(@PathVariable Long id) {
    Optional<Catalogo> catalogoOptional = catalogoService.findById(id);
    if (catalogoOptional.isPresent()) {
        catalogoService.delete(catalogoOptional.get());
        return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
}
}
