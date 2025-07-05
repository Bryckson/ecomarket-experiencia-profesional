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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Usuarios", description="API de usuario")
@RestController
@RequestMapping("api/Usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServices usuarioservice;

    @Operation(summary = "Obtener lista de usuarios", description = "Devuelve todos los usuarios disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de usuario retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Usuario.class)))

    @GetMapping
    public List<Usuario> mostrarUsuarios(){
        return usuarioservice.findByAll();
    }

    @Operation(summary = "Obtener usuario por ID", description = "Obtiene el detalle de un usuario específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "usuario encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioservice.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "usuario creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario unUsuario){
        return ResponseEntity.status (HttpStatus.CREATED). body(usuarioservice.save(unUsuario));
    }

    @Operation(summary = "Modificar usuario por ID", description = "Modificar información de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "usuario modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")
    })

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

    @Operation(summary = "Eliminar usuario por ID", description = "Elimina un objeto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "usuario eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")
    })
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario (@PathVariable Long id){
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional <Usuario> usuarioOptional = usuarioservice.delete(unUsuario);
        if (usuarioOptional.isPresent()){
             return ResponseEntity.noContent().build(); //  204 No Content
        }
        return ResponseEntity.notFound().build();
    }    
}

