package com.ecomarket.springboot_rest.fullrest.restcontrollers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;
import com.ecomarket.springboot_rest.fullrest.services.NotificacionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificacionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NotificacionServiceImpl NotificacionServiceImpl;  // mocks, objeto simulado

    @Autowired
    private ObjectMapper objectMapper;  // atributo para gestionar el método save

    List<Notificacion> listaNotificaciones;

    @Test
    public void verNotificaciones() throws Exception {
        when(NotificacionServiceImpl.findByAll()).thenReturn(listaNotificaciones);
        mockMvc.perform(get("/api/notificaciones")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verUnaNotificacion() {
        Notificacion unaNotificacion = new Notificacion(1L, 1L, "sistema", "Mensaje de prueba", LocalDateTime.now(), false);
        try {
            when(NotificacionServiceImpl.findById(1L)).thenReturn(Optional.of(unaNotificacion));
            mockMvc.perform(get("/api/notificaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Error en el test " + ex.getMessage());
        }
    }

    @Test
    public void crearNotificacionTest() throws Exception {
        Notificacion notificacionEntrada = new Notificacion(null, 2L, "promocion", "Nueva promo", LocalDateTime.now(), false);
        Notificacion notificacionGuardada = new Notificacion(2L, 2L, "promocion", "Nueva promo", LocalDateTime.now(), false);

        when(NotificacionServiceImpl.save(any(Notificacion.class))).thenReturn(notificacionGuardada);

        mockMvc.perform(post("/api/notificaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(notificacionEntrada)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarNotificacionTest() throws Exception {
        Notificacion notificacionEntrada = new Notificacion(null, 3L, "recordatorio", "Actualizar datos", LocalDateTime.now(), false);
        Notificacion notificacionModificada = new Notificacion(3L, 3L, "recordatorio", "Actualizar datos", LocalDateTime.now(), true);

        // Simula que la notificación con ID 3 sí existe
        when(NotificacionServiceImpl.findById(3L)).thenReturn(Optional.of(notificacionModificada));
        when(NotificacionServiceImpl.save(any(Notificacion.class))).thenReturn(notificacionModificada);

        mockMvc.perform(put("/api/notificaciones/3")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(notificacionEntrada)))
            .andExpect(status().isOk());
    }

    @Test
    public void notificacionNoExisteTest() throws Exception {
        when(NotificacionServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/notificaciones/99")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarNotificacionTest() throws Exception {
        Notificacion notificacionExistente = new Notificacion(4L, 4L, "sistema", "Mensaje a eliminar", LocalDateTime.now(), true);

        // Simula que la notificación con ID 4 fue encontrada y eliminada
        when(NotificacionServiceImpl.delete(any(Notificacion.class))).thenReturn(Optional.of(notificacionExistente));

        mockMvc.perform(delete("/api/notificaciones/4"))
            .andExpect(status().isNoContent());
    }
}
