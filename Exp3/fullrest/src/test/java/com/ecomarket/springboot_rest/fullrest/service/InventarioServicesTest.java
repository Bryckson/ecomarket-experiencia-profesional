    package com.ecomarket.springboot_rest.fullrest.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.repositories.InventarioRepository;
import com.ecomarket.springboot_rest.fullrest.services.InventarioServiceImpl;

@SpringBootTest
public class InventarioServicesTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioServiceImpl inventarioServiceImpl;

    private Inventario unInventario;

    @BeforeEach
    public void Iniciar() {
        unInventario = new Inventario(1L, "Producto Test", "Categoria Test", 100, 999.99, true);
    }

    @Test
    public void findByAllTest() {
        List<Inventario> lista = Arrays.asList(unInventario);
        when(inventarioRepository.findAll()).thenReturn(lista);
        List<Inventario> resultado = inventarioServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(inventarioRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(unInventario));
        Optional<Inventario> resultado = inventarioServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.get().getNombreProducto());
        verify(inventarioRepository).findById(1L);
    }

    @Test
    public void saveTest() {
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(unInventario);
        Inventario resultado = inventarioServiceImpl.save(unInventario);
        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getNombreProducto());
        verify(inventarioRepository).save(unInventario);
    }

   @Test
public void deleteTest() {
    Long inventarioId = 1L;
    Inventario inventarioExistente = new Inventario(inventarioId, "Producto Test", "Categoria Test", 100, 999.99, true);

    // Simula que existe en BD
    when(inventarioRepository.findById(inventarioId)).thenReturn(Optional.of(inventarioExistente));

    // Llamada real → delete(Long id)
    inventarioServiceImpl.delete(inventarioId);

    // Verifica que deleteById fue llamado
    verify(inventarioRepository).deleteById(inventarioId);
}
}
