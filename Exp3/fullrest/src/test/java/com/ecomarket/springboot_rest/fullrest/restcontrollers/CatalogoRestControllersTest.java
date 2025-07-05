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

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;
import com.ecomarket.springboot_rest.fullrest.services.CatalogoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogoRestControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CatalogoServiceImpl catalogoServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    List<Catalogo> listaCatalogos;

    @Test
    public void verCatalogos() throws Exception {
        when(catalogoServiceImpl.findByAll()).thenReturn(listaCatalogos);
        mockMvc.perform(get("/api/Catalogos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verUnCatalogo() {
        Catalogo unCatalogo = new Catalogo(1L, "Producto Eco", "Descripción de prueba", "Categoría 1", 5000.0, "Sucursal Centro", true);
        try {
            when(catalogoServiceImpl.findById(1L)).thenReturn(Optional.of(unCatalogo));
            mockMvc.perform(get("/api/Catalogos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Error en el test " + ex.getMessage());
        }
    }

    @Test
    public void crearCatalogoTest() throws Exception {
        Catalogo catalogoEntrada = new Catalogo(null, "Producto Nuevo", "Nuevo producto", "Categoría Nueva", 12000.0, "Sucursal Norte", true);
        Catalogo catalogoGuardado = new Catalogo(2L, "Producto Nuevo", "Nuevo producto", "Categoría Nueva", 12000.0, "Sucursal Norte", true);

        when(catalogoServiceImpl.save(any(Catalogo.class))).thenReturn(catalogoGuardado);

        mockMvc.perform(post("/api/Catalogos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(catalogoEntrada)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarCatalogoTest() throws Exception {
        Catalogo catalogoEntrada = new Catalogo(null, "Producto Actualizado", "Actualizado", "Categoría 2", 15000.0, "Sucursal Sur", true);
        Catalogo catalogoModificado = new Catalogo(3L, "Producto Actualizado", "Actualizado", "Categoría 2", 15000.0, "Sucursal Sur", true);

        when(catalogoServiceImpl.findById(3L)).thenReturn(Optional.of(catalogoModificado));
        when(catalogoServiceImpl.save(any(Catalogo.class))).thenReturn(catalogoModificado);

        mockMvc.perform(put("/api/Catalogos/3")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(catalogoEntrada)))
            .andExpect(status().isOk());
    }

    @Test
    public void catalogoNoExisteTest() throws Exception {
        when(catalogoServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/Catalogos/99")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarCatalogoTest() throws Exception {
        Catalogo catalogoExistente = new Catalogo(4L, "Producto Eliminado", "Eliminar", "Sin categoría", 0.0, "Sucursal Cerrada", false);

        when(catalogoServiceImpl.delete(any(Catalogo.class))).thenReturn(Optional.of(catalogoExistente));

        mockMvc.perform(delete("/api/Catalogos/4"))
            .andExpect(status().isNoContent());
    }
}
