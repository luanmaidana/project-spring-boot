package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.firstproject.firstproject.domain.PacoteViagem;
import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.services.PacoteViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pacotes")
public class PacoteViagemResource {

    @Autowired
    private PacoteViagemService service;

    
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public ResponseEntity<Optional<PacoteViagem>> findPacoteById(@PathVariable Integer id){

    
        try {
            Optional<PacoteViagem> cat = service.buscar(id);
            return new ResponseEntity<Optional<PacoteViagem>>(cat, HttpStatus.OK);
        } 
        catch (Exception e) {
        
            return new ResponseEntity<Optional<PacoteViagem>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<PacoteViagem>> findAll(){

        List<PacoteViagem> categorias = new ArrayList<>();

        categorias = service.buscarTodos();
       
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PacoteViagem> postCategoria(@AuthenticationPrincipal Usuario usuario, @RequestBody PacoteViagem obj){
        
        obj.setUsuarios(usuario);

        obj = service.addPacoteViagem(obj);
        
        return new ResponseEntity<>(obj, HttpStatus.OK);

    }


}
