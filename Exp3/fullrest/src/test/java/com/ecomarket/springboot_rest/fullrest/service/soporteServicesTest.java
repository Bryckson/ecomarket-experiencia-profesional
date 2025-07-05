package com.ecomarket.springboot_rest.fullrest.service;

import java.time.LocalDateTime;
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

import com.ecomarket.springboot_rest.fullrest.entities.Soporte;
import com.ecomarket.springboot_rest.fullrest.repositories.SoporteRepositories;
import com.ecomarket.springboot_rest.fullrest.services.SoporteServiceImpl;

@SpringBootTest
public class soporteServicesTest {

    @Mock
    private SoporteRepositories soporterepositories;

    @InjectMocks
    private SoporteServiceImpl soporteServiceImpl;

    private Soporte unsoporte; 

    @BeforeEach
    public void Iniciar(){
        unsoporte = new Soporte (true,"abierto", LocalDateTime.now(), null, null, 1L, "No recibí mi pedido completo.", null, "reclamo"); //agregar una fila de datos de la base de datos correspondiente
    }

    @Test
    public void findByAllTest(){
        List<Soporte> lista = Arrays.asList(unsoporte);
        when(soporterepositories.findAll()).thenReturn(lista);         //mockito
        List<Soporte> resultado = soporteServiceImpl.findByAll();
        assertEquals(1, resultado.size());                  //JUnit
        verify(soporterepositories).findAll();
    }

    @Test
    public void findByIdTest(){
        when(soporterepositories.findById(1L)).thenReturn(Optional.of(unsoporte));
        Optional<Soporte> resultado = soporteServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("abierto", resultado.get().getEstado()); 
        verify(soporterepositories).findById(1L);
        

    }

    @Test
    public void saveTest(){
        when(soporterepositories.save(any(Soporte.class))).thenReturn(unsoporte);
        Soporte resultado = soporteServiceImpl.save(unsoporte);
        assertNotNull(resultado);
        assertEquals("abierto", resultado.getEstado());
        verify(soporterepositories).save(unsoporte);
    }


    @Test
    public void deleteTest() {
        unsoporte.setId(1L);   // Se asigna ID al objeto de prueba   
        when(soporterepositories.findById(1L)).thenReturn(Optional.of(unsoporte)); //simula que el repo encuentra el objeto por ID    
        Optional<Soporte> eliminado = soporteServiceImpl.delete(unsoporte); // Se ejecuta el método delete del servicio    
        verify(soporterepositories).delete(unsoporte);// verifica que llamó al delete del repo   
        assertEquals(unsoporte, eliminado.get()); //valida el objeto eliminado
    }








}
