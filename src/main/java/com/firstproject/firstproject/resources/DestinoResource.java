package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;

import com.firstproject.firstproject.domain.Destino;
import com.firstproject.firstproject.model.ResponseModel;
import com.firstproject.firstproject.services.DestinoService;

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
@RequestMapping("/api/destino")
public class DestinoResource {
    
    @Autowired
    private DestinoService destinoService;

    // Método retorno uma lista de todos os destinos cadastrados
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Destino>> listarTodos() throws NotFoundExceptions{

        List<Destino> destinos = new ArrayList<>();

        try {
            destinos = destinoService.buscarTodos();
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, destinos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Ops ocorreu algum erro!", 400, destinos), HttpStatus.BAD_REQUEST);

        }
    }

    // Método retorna o destino correspondente ao id passado por parametro
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Destino>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        List<Destino> destinos = new ArrayList<>();

        Destino destino = new Destino();

        try {
            destino = destinoService.buscarPorId(id).get();

            destinos.add(destino);
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, destinos), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível localizar esse destino!", 404, destinos), HttpStatus.NOT_FOUND);
        }

        
    }


    // Método adiciona um novo destino
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseModel<Destino>> post(@RequestBody Destino destino){

        List<Destino> destinos = new ArrayList<>();

        try {
            destinoService.addDestino(destino);
            destinos.add(destino);
            return new ResponseEntity<>(new ResponseModel<>("Destino adicionado com sucesso!", 201, destinos), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível adicionar esse destino!", 400, destinos), HttpStatus.BAD_REQUEST);
        }

    }

    // Método atualiza o destino correspondente ao id passado por parametro
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<ResponseModel<Destino>> update(@PathVariable Integer id, @RequestBody Destino destino) throws NotFoundExceptions{

        List<Destino> destinos = new ArrayList<>();

        Destino d = destinoService.buscarPorId(id).get();

        try {
            
            destinoService.update(id, d);
            destinos.add(d);
            return new ResponseEntity<>(new ResponseModel<>("Destino atualizado com sucesso!", 200, destinos), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível atualizar esse destino!", 400, destinos), HttpStatus.BAD_REQUEST);
        }

    }

    // Método dele o destino correspondente ao id passado por parametro

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseModel<Destino>> delete(@PathVariable Integer id) throws NotFoundExceptions{

        List<Destino> destinos = new ArrayList<>();

        Destino d = destinoService.buscarPorId(id).get();

       try {
        destinoService.delete(id);
        destinos.add(d);
        return new ResponseEntity<>(new ResponseModel<>("Destino deletado com sucesso!", 200, destinos), HttpStatus.OK);
       } catch (Exception e) {
        return new ResponseEntity<>(new ResponseModel<>("Não foi possível deletar esse destino!", 400, destinos), HttpStatus.BAD_REQUEST);
        }

    }

}
