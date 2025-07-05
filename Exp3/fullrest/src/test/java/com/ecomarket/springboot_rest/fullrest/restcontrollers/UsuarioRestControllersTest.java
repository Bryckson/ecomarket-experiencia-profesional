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

import com.ecomarket.springboot_rest.fullrest.entities.Usuario;
import com.ecomarket.springboot_rest.fullrest.services.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioRestControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioServiceImpl UsuarioServiceImpl;  // mock simulado

    @Autowired
    private ObjectMapper objectMapper;

    List<Usuario> listaUsuarios;

    @Test
    public void verUsuarios() throws Exception {
        when(UsuarioServiceImpl.findByAll()).thenReturn(listaUsuarios);

        mockMvc.perform(get("/api/Usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnUsuario() {
        Usuario unUsuario = new Usuario(1L, "Bryckson Gutierrez", "bryc_gu@ecomarket.com", "$2a$10$claveHash", "VENDEDOR", true);
        try {
            when(UsuarioServiceImpl.findById(1L)).thenReturn(Optional.of(unUsuario));

            mockMvc.perform(get("/api/Usuarios/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Error.. en el test " + ex.getMessage());
        }
    }

    @Test
    public void usuarioNoExisteTest() throws Exception {
        when(UsuarioServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/Usuarios/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception {
        Usuario usuarioEntrada = new Usuario(null, "Nuevo Usuario", "nuevo@ecomarket.com", "$2a$10$nuevaClave", "GERENTE", true);
        Usuario usuarioGuardado = new Usuario(4L, "Nuevo Usuario", "nuevo@ecomarket.com", "$2a$10$nuevaClave", "GERENTE", true);

        when(UsuarioServiceImpl.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        mockMvc.perform(post("/api/Usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioEntrada)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarUsuarioTest() throws Exception {
        Usuario usuarioEntrada = new Usuario(null, "Modificado", "mod@ecomarket.com", "$2a$10$modificado", "GERENTE REGIONAL", true);
        Usuario usuarioModificado = new Usuario(5L, "Modificado", "mod@ecomarket.com", "$2a$10$modificado", "GERENTE REGIONAL", true);
        
            // Simula que el usuario con ID 5 sí existe
        when(UsuarioServiceImpl.findById(5L)).thenReturn(Optional.of(usuarioModificado));

        when(UsuarioServiceImpl.save(any(Usuario.class))).thenReturn(usuarioModificado);

        mockMvc.perform(put("/api/Usuarios/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioEntrada)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        Usuario usuarioExistente = new Usuario(6L, "Eliminar", "del@ecomarket.com", "$2a$10$hash", "GERENTE", true);

        // Simula que el usuario con ID 6 fue encontrado y eliminado
        when(UsuarioServiceImpl.delete(any(Usuario.class))).thenReturn(Optional.of(usuarioExistente));

        mockMvc.perform(delete("/api/Usuarios/6"))
            .andExpect(status().isNoContent());
    }

}
