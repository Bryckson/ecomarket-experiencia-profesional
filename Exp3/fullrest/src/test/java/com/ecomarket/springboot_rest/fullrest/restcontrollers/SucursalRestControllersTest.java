package com.ecomarket.springboot_rest.fullrest.restcontrollers;

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

import com.ecomarket.springboot_rest.fullrest.entities.Sucursal;
import com.ecomarket.springboot_rest.fullrest.services.SucursalServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SucursalRestControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SucursalServiceImpl sucursalServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    List<Sucursal> listaSucursales;

    @Test
    public void verSucursales() throws Exception {
        when(sucursalServiceImpl.findByAll()).thenReturn(listaSucursales);
        mockMvc.perform(get("/api/Sucursales")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verUnaSucursal() {
        Sucursal unaSucursal = new Sucursal(1L, "Sucursal Centro", "Av. Principal 123", "Santiago", "RM", "Zona Metropolitana", true);
        try {
            when(sucursalServiceImpl.findById(1L)).thenReturn(Optional.of(unaSucursal));
            mockMvc.perform(get("/api/Sucursales/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Error en el test " + ex.getMessage());
        }
    }

    @Test
    public void crearSucursalTest() throws Exception {
        Sucursal sucursalEntrada = new Sucursal(null, "Sucursal Norte", "Calle 1", "Antofagasta", "II", "Zona Norte", true);
        Sucursal sucursalGuardada = new Sucursal(2L, "Sucursal Norte", "Calle 1", "Antofagasta", "II", "Zona Norte", true);

        when(sucursalServiceImpl.save(any(Sucursal.class))).thenReturn(sucursalGuardada);

        mockMvc.perform(post("/api/Sucursales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sucursalEntrada)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarSucursalTest() throws Exception {
        Sucursal sucursalEntrada = new Sucursal(null, "Sucursal Sur", "Calle 99", "Puerto Montt", "X", "Zona Sur", false);
        Sucursal sucursalModificada = new Sucursal(3L, "Sucursal Sur", "Calle 99", "Puerto Montt", "X", "Zona Sur", true);

        when(sucursalServiceImpl.findById(3L)).thenReturn(Optional.of(sucursalModificada));
        when(sucursalServiceImpl.save(any(Sucursal.class))).thenReturn(sucursalModificada);

        mockMvc.perform(put("/api/Sucursales/3")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sucursalEntrada)))
            .andExpect(status().isOk());
    }

    @Test
    public void sucursalNoExisteTest() throws Exception {
        when(sucursalServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/Sucursales/99")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarSucursalTest() throws Exception {
        Sucursal sucursalExistente = new Sucursal(4L, "Sucursal Cerrada", "Calle cerrada", "Temuco", "IX", "Zona Sur", false);

        when(sucursalServiceImpl.delete(any(Sucursal.class))).thenReturn(Optional.of(sucursalExistente));

        mockMvc.perform(delete("/api/Sucursales/4"))
            .andExpect(status().isNoContent());
    }
}
