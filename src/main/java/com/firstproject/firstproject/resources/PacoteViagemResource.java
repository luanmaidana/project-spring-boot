package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;

import com.firstproject.firstproject.domain.PacoteViagem;
import com.firstproject.firstproject.dtos.PacoteViagemDTO;
import com.firstproject.firstproject.model.ResponseModel;
import com.firstproject.firstproject.services.PacoteViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import exceptions.NotFoundExceptions;


@RestController
@RequestMapping("/api/pacotes")
public class PacoteViagemResource {

    @Autowired
    private PacoteViagemService service;
    
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResponseModel<PacoteViagem>> findPacoteById(@PathVariable Integer id){

        List<PacoteViagem> pacotes = new ArrayList<>();
    
        try {
            PacoteViagem pacote = service.buscar(id).get();
            pacotes.add(pacote);
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, pacotes), HttpStatus.OK);
        } 
        catch (Exception e) {
        
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível localizar esse pacote de viagem!", 404, pacotes), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ResponseModel<PacoteViagem>> findAll(){

        List<PacoteViagem> pacotes = new ArrayList<>();

        try {
            pacotes = service.buscarTodos();
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, pacotes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Ops, algo deu errado!", 400, pacotes), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseModel<PacoteViagem>> postCategoria(@RequestBody PacoteViagemDTO obj){
        
        List<PacoteViagem> pacotes = new ArrayList<>();

        try {

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String username;    

            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            pacotes.add(service.insertPacoteViagem(obj, username));

            return new ResponseEntity<>(new ResponseModel<>("Pacote de Viagem adicionado com sucesso", 201, pacotes), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível adicionar esse pacote de viagem!", 400, pacotes), HttpStatus.BAD_REQUEST);
        }
        
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<ResponseModel<PacoteViagem>> update(@PathVariable Integer id,@RequestBody PacoteViagemDTO pacoteViagemDTO) throws NotFoundExceptions{

        List<PacoteViagem> pacotes = new ArrayList<>();

        try {
            PacoteViagem x = service.update(id, pacoteViagemDTO);
            pacotes.add(x);

            return new ResponseEntity<>(new ResponseModel<>("Pacote de viagem atualizado com sucesso!", 200, pacotes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível atualizar esse pacote de viagem!", 400, pacotes), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ResponseModel<PacoteViagem>> delete(@PathVariable Integer id){

        List<PacoteViagem> pacotes = new ArrayList<>();

        PacoteViagem pacote = new PacoteViagem();

        try {
            pacote = service.buscar(id).get();
            pacotes.add(pacote);
            service.delete(pacote);

            return new ResponseEntity<>(new ResponseModel<>("Pacote de viagem deletado com sucesso!", 200, pacotes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível deletar pacote de viagem!", 400, pacotes), HttpStatus.BAD_REQUEST);

        }

    }
}
