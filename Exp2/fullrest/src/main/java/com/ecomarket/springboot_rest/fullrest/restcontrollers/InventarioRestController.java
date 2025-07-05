package com.ecomarket.springboot_rest.fullrest.restcontrollers;

import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.services.InventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/inventario")
    public class InventarioRestController {

        @Autowired
        private InventarioService inventarioService;

        // Obtener todos los registros
        @GetMapping
        public List<Inventario> listarInventario() {
            return inventarioService.findByAll();
        }

        // Obtener un inventario por ID
        @GetMapping("/{id}")
        public ResponseEntity<?> obtenerInventario(@PathVariable Long id) {
            Optional<Inventario> inventarioOptional = inventarioService.findById(id);
            if (inventarioOptional.isPresent()) {
                return ResponseEntity.ok(inventarioOptional.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventario no encontrado con ID: " + id);
        }

        // Crear nuevo inventario
        @PostMapping
        public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
            Inventario nuevo = inventarioService.save(inventario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        }

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
