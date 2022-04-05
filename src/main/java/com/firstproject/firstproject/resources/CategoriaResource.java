package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.firstproject.firstproject.domain.Categoria;
import com.firstproject.firstproject.services.CategoriaService;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import exceptions.NotFoundExceptions;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public ResponseEntity<Optional<Categoria>> findCategoria(@PathVariable Integer id){

    
        try {
            Optional<Categoria> cat = service.buscar(id);
            return new ResponseEntity<Optional<Categoria>>(cat, HttpStatus.OK);
        } 
        catch (Exception e) {
        
            return new ResponseEntity<Optional<Categoria>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Categoria>> findAll(){

        List<Categoria> categorias = new ArrayList<>();

        categorias = service.buscarTodos();
       
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria obj){

        obj = service.addCategoria(obj);

        return new ResponseEntity<>(obj, HttpStatus.OK);

    }


}
