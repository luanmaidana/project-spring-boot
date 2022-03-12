package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;

import com.firstproject.firstproject.domain.Categoria;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Categoria> listar(){

        Categoria cat = new Categoria(1, "informática");
        Categoria cat2 = new Categoria(2, "Escritório");

        List<Categoria> lista = new ArrayList<>();

        lista.add(cat);
        lista.add(cat2);

        return lista;
    }
}
