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

import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;
import com.ecomarket.springboot_rest.fullrest.repositories.NotificacionRepository;
import com.ecomarket.springboot_rest.fullrest.services.NotificacionServiceImpl;

@SpringBootTest
public class NotificacionServicesTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionServiceImpl notificacionServiceImpl;

    private Notificacion unaNotificacion;

    @BeforeEach
    public void Iniciar() {
        unaNotificacion = new Notificacion(1L, 1L, "sistema", "Mensaje de prueba", LocalDateTime.now(), false);
    }

    @Test
    public void findByAllTest() {
        List<Notificacion> lista = Arrays.asList(unaNotificacion);
        when(notificacionRepository.findAll()).thenReturn(lista);
        List<Notificacion> resultado = notificacionServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(notificacionRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(unaNotificacion));
        Optional<Notificacion> resultado = notificacionServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("sistema", resultado.get().getTipo());
        verify(notificacionRepository).findById(1L);
    }

    @Test
    public void saveTest() {
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(unaNotificacion);
        Notificacion resultado = notificacionServiceImpl.save(unaNotificacion);
        assertNotNull(resultado);
        assertEquals("sistema", resultado.getTipo());
        verify(notificacionRepository).save(unaNotificacion);
    }

    @Test
    public void deleteTest() {
        unaNotificacion.setId(1L);
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(unaNotificacion));
        Optional<Notificacion> eliminado = notificacionServiceImpl.delete(unaNotificacion);
        verify(notificacionRepository).delete(unaNotificacion);
        assertEquals(unaNotificacion, eliminado.get());
    }

}
