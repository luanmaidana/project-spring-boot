package com.firstproject.firstproject.resources;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Viagem;
import com.firstproject.firstproject.dtos.ViagemDTO;
import com.firstproject.firstproject.services.ViagemService;

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
@RequestMapping("/api/viagem")
public class ViagemResource {
    
    @Autowired
    private ViagemService viagemService;

    @GetMapping()
    public ResponseEntity<List<Viagem>> listarTodos(){

        return ResponseEntity.ok(viagemService.buscarTodos());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Viagem>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Optional<Viagem> usuario = viagemService.buscarPorId(id);

        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ViagemDTO> post(@RequestBody ViagemDTO viagem){

        viagemService.insertViagem(viagem);

        return new ResponseEntity<>(viagem, HttpStatus.OK);

    }

}
