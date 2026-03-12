package Controllers;

package com.upiiz.CRUD.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/dispositivos")
public class DispositivoController {

    List<Map<String,String>> dispositivos = new ArrayList<>();

    public DispositivoController(){

        agregar("1","Laptop","Computadora","Dell","XPS 13");
        agregar("2","Celular","Telefono","Samsung","S23");
        agregar("3","Tablet","Tablet","Apple","iPad");
        agregar("4","Smartwatch","Reloj","Huawei","GT4");
        agregar("5","Monitor","Pantalla","LG","UltraWide");
        agregar("6","Teclado","Periferico","Logitech","K120");
        agregar("7","Mouse","Periferico","HP","M100");
        agregar("8","Router","Red","TPLink","AX3000");
        agregar("9","Impresora","Oficina","Epson","L3250");
        agregar("10","Camara","Video","Sony","Alpha");

    }

    private void agregar(String id,String nombre,String tipo,String marca,String modelo){

        Map<String,String> d = new HashMap<>();

        d.put("id",id);
        d.put("nombre",nombre);
        d.put("tipo",tipo);
        d.put("marca",marca);
        d.put("modelo",modelo);

        dispositivos.add(d);
    }

    @GetMapping
    public String lista(Model model){

        model.addAttribute("dispositivos",dispositivos);
        return "lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(){
        return "crear";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String id,
                          @RequestParam String nombre,
                          @RequestParam String tipo,
                          @RequestParam String marca,
                          @RequestParam String modelo){

        agregar(id,nombre,tipo,marca,modelo);

        return "redirect:/dispositivos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model){

        for(Map<String,String> d : dispositivos){

            if(d.get("id").equals(id)){
                model.addAttribute("dispositivo",d);
            }

        }

        return "editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@RequestParam String id,
                             @RequestParam String nombre,
                             @RequestParam String tipo,
                             @RequestParam String marca,
                             @RequestParam String modelo){

        for(Map<String,String> d : dispositivos){

            if(d.get("id").equals(id)){

                d.put("nombre",nombre);
                d.put("tipo",tipo);
                d.put("marca",marca);
                d.put("modelo",modelo);

            }

        }

        return "redirect:/dispositivos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, Model model){

        for(Map<String,String> d : dispositivos){

            if(d.get("id").equals(id)){
                model.addAttribute("dispositivo",d);
            }

        }

        return "eliminar";
    }

    @PostMapping("/borrar")
    public String borrar(@RequestParam String id){

        dispositivos.removeIf(d -> d.get("id").equals(id));

        return "redirect:/dispositivos";
    }

}
