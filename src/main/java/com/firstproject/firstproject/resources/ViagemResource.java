package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;
import com.firstproject.firstproject.domain.Viagem;
import com.firstproject.firstproject.dtos.ViagemDTO;
import com.firstproject.firstproject.model.ResponseModel;
import com.firstproject.firstproject.services.ViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Método retorna todas as viagens cadastradas
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Viagem>> listarTodos(){

        List<Viagem> viagens = new ArrayList<>();

        try {
            viagens = viagemService.buscarTodos();
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, viagens), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Viagem não encontrada!", 404, viagens),HttpStatus.NOT_FOUND);
        }

        
    }

    // Método retorna apenas a viagem do id passado por parametro na url
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Viagem>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Viagem viagem = new Viagem();
        
        List<Viagem> viagens = new ArrayList<>();
       
        try {
            viagem  = viagemService.buscarPorId(id).get();
            viagens.add(viagem);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Viagem não encontrada!", 404, viagens),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, viagens), HttpStatus.OK);
        
    }

    // Método cria nova viagem
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseModel<ViagemDTO>> post(@RequestBody ViagemDTO viagem){

        List<ViagemDTO> viagens = new ArrayList<>();

        try {
            viagens.add(viagem);
            viagemService.insert(viagem);
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 201, viagens), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível adicionar uma nova viagem!", 400, viagens), HttpStatus.BAD_REQUEST);
        }


    }

    // Método deleta uma viagem pelo id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseModel<Viagem>> delete(@PathVariable Integer id) throws NotFoundExceptions{

        List<Viagem> viagens = new ArrayList<>();

        Viagem viagem = viagemService.buscarPorId(id).get();

        if(viagem == null){
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível localizar essa viagem!", 404, viagens), HttpStatus.NOT_FOUND);
        }

        try {
            viagemService.delete(id);
            viagens.add(viagem);
            return new ResponseEntity<>(new ResponseModel<>("Viagem deletada com sucesso!", 200, viagens), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível deletar essa viagem!", 400, viagens), HttpStatus.BAD_REQUEST);
        }

    }

    // Método atualiza viagem correspondendo ai id
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<ResponseModel<Viagem>> update(@PathVariable Integer id, @RequestBody ViagemDTO viagemDTO) throws NotFoundExceptions{

        List<Viagem> viagens = new ArrayList<>();

        Viagem viagem = new Viagem();

        try {
            viagem = viagemService.update(id, viagemDTO);
            viagens.add(viagem);
            return new ResponseEntity<>(new ResponseModel<Viagem>("Viagem atualizada com sucesso!", 200, viagens), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível atualizar essa viagem!", 400, viagens), HttpStatus.BAD_REQUEST);
        }


    }

}
