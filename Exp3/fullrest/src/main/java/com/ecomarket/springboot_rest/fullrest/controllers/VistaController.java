package com.ecomarket.springboot_rest.fullrest.controllers;

import java.util.List;
import com.ecomarket.springboot_rest.fullrest.entities.Notificacion;
import com.ecomarket.springboot_rest.fullrest.repositories.NotificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecomarket.springboot_rest.fullrest.entities.Catalogo;
import com.ecomarket.springboot_rest.fullrest.entities.Inventario;
import com.ecomarket.springboot_rest.fullrest.entities.Soporte;
import com.ecomarket.springboot_rest.fullrest.entities.Usuario;
import com.ecomarket.springboot_rest.fullrest.entities.Sucursal;

import com.ecomarket.springboot_rest.fullrest.repositories.CatalogoRepositories;
import com.ecomarket.springboot_rest.fullrest.repositories.InventarioRepository;
import com.ecomarket.springboot_rest.fullrest.repositories.SoporteRepositories;
import com.ecomarket.springboot_rest.fullrest.repositories.UsuarioRepositories;
import com.ecomarket.springboot_rest.fullrest.repositories.SucursalRepositories;

@Controller
public class VistaController {

    @Autowired
    private UsuarioRepositories usuariorepositories;

    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        List<Usuario> usuariditos = (List<Usuario>) usuariorepositories.findAll();
        model.addAttribute("usuarios", usuariditos); 
        return "usuario";
    }

    @Autowired
    private CatalogoRepositories catalogorepositories;

    @GetMapping("/catalogos")
    public String verCatalogos(Model model) {
        List<Catalogo> catalogitos = (List<Catalogo>) catalogorepositories.findAll();
        model.addAttribute("catalogo", catalogitos); 
        return "catalogo";
    }    

    @Autowired
    private InventarioRepository inventariopositories;

    @GetMapping("/inventario")
    public String listarInventario(Model model) {
        List<Inventario> invent = (List<Inventario>) inventariopositories.findAll();
        model.addAttribute("inventario", invent);
        return "inventario";  
    }

    @Autowired
    private SoporteRepositories soporterepositories;

    @GetMapping("/soportes")
    public String verSoportes(Model model) {
        List<Soporte> soportitos = (List<Soporte>) soporterepositories.findAll();
        model.addAttribute("soportes", soportitos); 
        return "soporte";
    }

    @Autowired
    private SucursalRepositories sucursalrepositories;

    @GetMapping("/sucursales")
    public String verSucursales(Model model) {
        List<Sucursal> sucursalitas = (List<Sucursal>) sucursalrepositories.findAll();
        model.addAttribute("sucursales", sucursalitas); 
        return "sucursal";
    }
    @Autowired
private NotificacionRepository notificacionRepository;  

@GetMapping("/notificaciones")
public String verNotificaciones(Model model) {
    List<Notificacion> notificaciones = (List<Notificacion>) notificacionRepository.findAll();
    model.addAttribute("notificaciones", notificaciones);
    return "notificacion";  // el nombre del HTML va en minúsculas como lo tienes en los demás
}
}
