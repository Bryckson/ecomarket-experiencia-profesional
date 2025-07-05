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

import com.ecomarket.springboot_rest.fullrest.entities.Soporte;
import com.ecomarket.springboot_rest.fullrest.services.SoporteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SoporteRestControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SoporteServiceImpl SoporteServiceImpl;        //mocks, objeto simulado

    @Autowired
    private ObjectMapper objectMapper;              //atributo para gestionar el método save

    List<Soporte> listaSoportes;


    @Test
    public void verSoportes() throws Exception{
        when(SoporteServiceImpl.findByAll()).thenReturn(listaSoportes);
        mockMvc.perform(get("/api/Soportes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    @Test
    public void verUnSoporte(){
        Soporte unSoporte = new Soporte (true,"abierto", LocalDateTime.now(), null, null, 1L, "No recibí mi pedido completo.", null, "reclamo"); //agregar una fila de datos de la base de datos correspondiente
        try{
            when(SoporteServiceImpl.findById(1L)).thenReturn(Optional.of(unSoporte));
            mockMvc.perform(get("/api/Soportes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        }catch (Exception ex){
            fail("Error.. en el test " + ex.getMessage());
        }
    }

    @Test
    public void crearSoporteTest() throws Exception {
        Soporte soporteEntrada = new Soporte(null, "nuevo", LocalDateTime.now(), null, null, 1L, "Problema de stock", null, "consulta");
        Soporte soporteGuardado = new Soporte(true, "nuevo", LocalDateTime.now(), null, 4L, 1L, "Problema de stock", null, "consulta");

        when(SoporteServiceImpl.save(any(Soporte.class))).thenReturn(soporteGuardado);

        mockMvc.perform(post("/api/Soportes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(soporteEntrada)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarSoporteTest() throws Exception {
        Soporte soporteEntrada = new Soporte(null, "en_progreso", LocalDateTime.now().minusDays(1), null, null, 1L, "Problema sin resolver", null, "reclamo");
        Soporte soporteModificado = new Soporte(true, "en_progreso", LocalDateTime.now().minusDays(1), null, 5L, 1L, "Problema sin resolver", null, "reclamo");

        //  Simula que el soporte con ID 5 sí existe
        when(SoporteServiceImpl.findById(5L)).thenReturn(Optional.of(soporteModificado));

        when(SoporteServiceImpl.save(any(Soporte.class))).thenReturn(soporteModificado);

        mockMvc.perform(put("/api/Soportes/5")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(soporteEntrada)))
            .andExpect(status().isOk());
    }


    @Test
    public void soporteNoExisteTest() throws Exception {
        when(SoporteServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/Soportes/99")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarSoporteTest() throws Exception {
        Soporte soporteExistente = new Soporte(true, "cerrado", LocalDateTime.now(), null, 6L, 1L, "Todo resuelto", "Gracias", "consulta");

    // Simula que el soporte con ID 6 fue encontrado y eliminado
        when(SoporteServiceImpl.delete(any(Soporte.class))).thenReturn(Optional.of(soporteExistente));

        mockMvc.perform(delete("/api/Soportes/6"))
        .andExpect(status().isNoContent());
    }


}
