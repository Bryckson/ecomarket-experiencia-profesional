package com.ecomarket.springboot_rest.fullrest.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.springboot_rest.fullrest.entities.Usuario;
import com.ecomarket.springboot_rest.fullrest.services.UsuarioServices;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServices usuarioservice;

    @GetMapping
    public List<Usuario> mostrarUsuarios(){
        return usuarioservice.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioservice.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario unUsuario){
        return ResponseEntity.status (HttpStatus.CREATED). body(usuarioservice.save(unUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario unUsuario){
        Optional<Usuario> usuarioOptional = usuarioservice.findById(id);
        if (usuarioOptional.isPresent()){
            Usuario usuarioexistente = usuarioOptional.get();
            usuarioexistente.setNombre(unUsuario.getNombre());
            usuarioexistente.setEmail(unUsuario.getEmail());
            usuarioexistente.setPasswordHash(unUsuario.getPasswordHash());
            usuarioexistente.setRol(unUsuario.getRol());
            usuarioexistente.setActivo(unUsuario.getActivo());
            Usuario productomodificado = usuarioservice.save(usuarioexistente);
            return ResponseEntity.ok(productomodificado);

        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario (@PathVariable Long id){
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional <Usuario> usuarioOptional = usuarioservice.delete(unUsuario);
        if (usuarioOptional.isPresent()){
             return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }    
}

