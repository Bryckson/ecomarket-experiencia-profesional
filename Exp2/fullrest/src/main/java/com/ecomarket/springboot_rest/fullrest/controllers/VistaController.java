package com.ecomarket.springboot_rest.fullrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;
import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.entities.Usuario;
import com.ecomarket.springboot_rest.fullrest.repositories.CatalogoRepositories;
import com.ecomarket.springboot_rest.fullrest.repositories.InventarioRepository; 
//Esta línea inyecta una instancia de InventarioRepository en el controlador. Esto significa
// que Spring Boot se encarga de crear un objeto funcional de InventarioRepository,
// permitiéndote usar métodos como findAll() sin instanciar manualmente.
import com.ecomarket.springboot_rest.fullrest.repositories.UsuarioRepositories;

@Controller
public class VistaController {

    @Autowired
    private UsuarioRepositories usuariorepositories;

    @GetMapping("/usuarios")
    public String verUsuarios(Model model){
        List<Usuario> usuariditos = (List<Usuario>) usuariorepositories.findAll();
        model.addAttribute("usuarios", usuariditos); 
        return "usuario";

    }


    @Autowired
    private CatalogoRepositories catalogorepositories;

    @GetMapping("/catalogos")
    public String verCatalogos(Model model){
        List<Catalogo> catalogitos = (List<Catalogo>) catalogorepositories.findAll();
        model.addAttribute("catalogo", catalogitos); 
        return "catalogo";

    }    

    @Autowired
    private InventarioRepository inventariopositories;

    @GetMapping("/inventario")//Este método se ejecutará cuando el navegador acceda a la UR (GET)
    public String listarInventario(Model model) {
        List<Inventario> invent = (List<Inventario>) inventariopositories.findAll();
        model.addAttribute("inventario", invent);
        return "inventario";  



    }

}
