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

import com.ecomarket.springboot_rest.fullrest.entities.Usuario;
import com.ecomarket.springboot_rest.fullrest.repositories.UsuarioRepositories;
import com.ecomarket.springboot_rest.fullrest.services.UsuarioServiceImpl;

@SpringBootTest
public class usuarioServicesTest {

    @Mock
    private UsuarioRepositories usuariorepositories;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    private Usuario unusuario; 

    @BeforeEach
    public void Iniciar(){
        unusuario = new Usuario(1L, "Bryckson Gutierrez", "bryc_gu@ecomarket.com", "$2a$10$claveHash", "VENDEDOR", true);
    }

    @Test
    public void findByAllTest(){
        List<Usuario> lista = Arrays.asList(unusuario);
        when(usuariorepositories.findAll()).thenReturn(lista);         //mockito
        List<Usuario> resultado = usuarioServiceImpl.findByAll();
        assertEquals(1, resultado.size());                  //JUnit
        verify(usuariorepositories).findAll();
    }

    @Test
    public void findByIdTest(){
        when(usuariorepositories.findById(1L)).thenReturn(Optional.of(unusuario));
        Optional<Usuario> resultado = usuarioServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("Bryckson Gutierrez", resultado.get().getNombre()); 
        verify(usuariorepositories).findById(1L);
        

    }

    @Test
    public void saveTest(){
        when(usuariorepositories.save(any(Usuario.class))).thenReturn(unusuario);
        Usuario resultado = usuarioServiceImpl.save(unusuario);
        assertNotNull(resultado);
        assertEquals("Bryckson Gutierrez", resultado.getNombre());
        verify(usuariorepositories).save(unusuario);
    }


    @Test
    public void deleteTest() {
        unusuario.setId(1L); 

        when(usuariorepositories.findById(1L)).thenReturn(Optional.of(unusuario));

        Optional<Usuario> eliminado = usuarioServiceImpl.delete(unusuario);

        verify(usuariorepositories).delete(unusuario);
        assertEquals(unusuario, eliminado.get());
    }







}
