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

import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.services.InventarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class InventarioRestControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventarioServiceImpl inventarioServiceImpl;    // mocks, objeto simulado

    @Autowired
    private ObjectMapper objectMapper;                      // para serializar/deserializar JSON

    List<Inventario> listaInventario;

    @Test
    public void verInventario() throws Exception {
        when(inventarioServiceImpl.findByAll()).thenReturn(listaInventario);
        mockMvc.perform(get("/api/inventario")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verUnInventario() {
        Inventario unInventario = new Inventario(1L, "Producto Test", "Categoria Test", 10, 9990.0, true);
        try {
            when(inventarioServiceImpl.findById(1L)).thenReturn(Optional.of(unInventario));
            mockMvc.perform(get("/api/inventario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Error en el test: " + ex.getMessage());
        }
    }

    @Test
    public void crearInventarioTest() throws Exception {
        Inventario inventarioEntrada = new Inventario(null, "Producto Nuevo", "Categoria Nueva", 20, 4990.0, true);
        Inventario inventarioGuardado = new Inventario(2L, "Producto Nuevo", "Categoria Nueva", 20, 4990.0, true);

        when(inventarioServiceImpl.save(any(Inventario.class))).thenReturn(inventarioGuardado);

        mockMvc.perform(post("/api/inventario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(inventarioEntrada)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarInventarioTest() throws Exception {
        Inventario inventarioEntrada = new Inventario(null, "Producto Modificado", "Categoria Mod", 50, 5990.0, false);
        Inventario inventarioModificado = new Inventario(3L, "Producto Modificado", "Categoria Mod", 50, 5990.0, false);

        when(inventarioServiceImpl.findById(3L)).thenReturn(Optional.of(inventarioModificado));
        when(inventarioServiceImpl.save(any(Inventario.class))).thenReturn(inventarioModificado);

        mockMvc.perform(put("/api/inventario/3")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(inventarioEntrada)))
            .andExpect(status().isOk());
    }

    @Test
    public void inventarioNoExisteTest() throws Exception {
        when(inventarioServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/inventario/99")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarInventarioTest() throws Exception {
        Inventario inventarioExistente = new Inventario(4L, "Producto Eliminado", "Categoria X", 0, 0.0, false);

        when(inventarioServiceImpl.findById(4L)).thenReturn(Optional.of(inventarioExistente));

        // Simula eliminación (aunque en tu delete en Service es void, te ajusto al flujo que tienes)
        mockMvc.perform(delete("/api/inventario/4"))
            .andExpect(status().isOk());
    }
}
