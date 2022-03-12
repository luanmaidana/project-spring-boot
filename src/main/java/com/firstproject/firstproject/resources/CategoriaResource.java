package com.firstproject.firstproject.resources;

import java.util.Optional;

import com.firstproject.firstproject.domain.Categoria;
import com.firstproject.firstproject.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public ResponseEntity<?> findCategoria(@PathVariable Integer id){

        Optional<Categoria> cat = service.buscar(id);
       
        return ResponseEntity.ok().body(cat);
    }

}
