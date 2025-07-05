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

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;
import com.ecomarket.springboot_rest.fullrest.repositories.CatalogoRepositories;
import com.ecomarket.springboot_rest.fullrest.services.CatalogoServiceImpl;

@SpringBootTest
public class catalogoServicesTest {

    @Mock
    private CatalogoRepositories catalogoRepositories;

    @InjectMocks
    private CatalogoServiceImpl catalogoServiceImpl;

    private Catalogo uncatalogo;

    @BeforeEach
    public void Iniciar() {
        uncatalogo = new Catalogo(1L, "Producto Test", "Descripción test", "Categoría Test", 9990.0, "Sucursal Test", true);
    }

    @Test
    public void findByAllTest() {
        List<Catalogo> lista = Arrays.asList(uncatalogo);
        when(catalogoRepositories.findAll()).thenReturn(lista);
        List<Catalogo> resultado = catalogoServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(catalogoRepositories).findAll();
    }

    @Test
    public void findByIdTest() {
        when(catalogoRepositories.findById(1L)).thenReturn(Optional.of(uncatalogo));
        Optional<Catalogo> resultado = catalogoServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.get().getNombre());
        verify(catalogoRepositories).findById(1L);
    }

    @Test
    public void saveTest() {
        when(catalogoRepositories.save(any(Catalogo.class))).thenReturn(uncatalogo);
        Catalogo resultado = catalogoServiceImpl.save(uncatalogo);
        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getNombre());
        verify(catalogoRepositories).save(uncatalogo);
    }

    @Test
    public void deleteTest() {
        uncatalogo.setId(1L);
        when(catalogoRepositories.findById(1L)).thenReturn(Optional.of(uncatalogo));
        Optional<Catalogo> eliminado = catalogoServiceImpl.delete(uncatalogo);
        verify(catalogoRepositories).delete(uncatalogo);
        assertEquals(uncatalogo, eliminado.get());
    }
}
