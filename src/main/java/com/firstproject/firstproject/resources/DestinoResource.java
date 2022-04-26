package com.firstproject.firstproject.resources;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Destino;
import com.firstproject.firstproject.services.DestinoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import exceptions.NotFoundExceptions;

@RestController
@RequestMapping("/api/destino")
public class DestinoResource {
    
    @Autowired
    private DestinoService destinoService;

    @GetMapping()
    public ResponseEntity<List<Destino>> listarTodos(){

        return ResponseEntity.ok(destinoService.buscarTodos());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Destino>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Optional<Destino> usuario = destinoService.buscarPorId(id);

        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Destino> post(@RequestBody Destino destino){

        destinoService.addDestino(destino);

        return new ResponseEntity<>(destino, HttpStatus.OK);

    }

}
