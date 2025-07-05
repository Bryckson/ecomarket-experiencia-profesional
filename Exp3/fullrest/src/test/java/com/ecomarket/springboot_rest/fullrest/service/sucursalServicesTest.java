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

import com.ecomarket.springboot_rest.fullrest.entities.Sucursal;
import com.ecomarket.springboot_rest.fullrest.repositories.SucursalRepositories;
import com.ecomarket.springboot_rest.fullrest.services.SucursalServiceImpl;

@SpringBootTest
public class sucursalServicesTest {

    @Mock
    private SucursalRepositories sucursalRepositories;

    @InjectMocks
    private SucursalServiceImpl sucursalServiceImpl;

    private Sucursal unasucursal;

    @BeforeEach
    public void Iniciar() {
        unasucursal = new Sucursal(1L, "Sucursal Centro", "Av. Principal 123", "Santiago", "RM", "Zona Metropolitana", true);
    }

    @Test
    public void findByAllTest() {
        List<Sucursal> lista = Arrays.asList(unasucursal);
        when(sucursalRepositories.findAll()).thenReturn(lista);
        List<Sucursal> resultado = sucursalServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(sucursalRepositories).findAll();
    }

    @Test
    public void findByIdTest() {
        when(sucursalRepositories.findById(1L)).thenReturn(Optional.of(unasucursal));
        Optional<Sucursal> resultado = sucursalServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("Sucursal Centro", resultado.get().getNombre());
        verify(sucursalRepositories).findById(1L);
    }

    @Test
    public void saveTest() {
        when(sucursalRepositories.save(any(Sucursal.class))).thenReturn(unasucursal);
        Sucursal resultado = sucursalServiceImpl.save(unasucursal);
        assertNotNull(resultado);
        assertEquals("Sucursal Centro", resultado.getNombre());
        verify(sucursalRepositories).save(unasucursal);
    }

    @Test
    public void deleteTest() {
        unasucursal.setId(1L);
        when(sucursalRepositories.findById(1L)).thenReturn(Optional.of(unasucursal));
        Optional<Sucursal> eliminado = sucursalServiceImpl.delete(unasucursal);
        verify(sucursalRepositories).delete(unasucursal);
        assertEquals(unasucursal, eliminado.get());
    }
}
