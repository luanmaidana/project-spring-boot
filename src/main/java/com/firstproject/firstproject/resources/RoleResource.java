package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;

import com.firstproject.firstproject.domain.Roles;
import com.firstproject.firstproject.dtos.RoleDTO;
import com.firstproject.firstproject.model.ResponseModel;
import com.firstproject.firstproject.services.RoleService;

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
@RequestMapping("/api/roles")
public class RoleResource {
    

    @Autowired
    private RoleService roleService;

    // Método retorno todos as roles cadastradas
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Roles>> listarTodos(){

        List<Roles> roles = new ArrayList<>();

        try {
            roles = roleService.buscarTodos();
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, roles), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Ops, ocorreu algum problema!", 400, roles), HttpStatus.BAD_REQUEST);

        }

    }

    // Retorna a role pelo id
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Roles>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Roles role = new Roles();
        List<Roles> roles = new ArrayList<>();

        try {
            role = roleService.buscarPorId(id).get();
            roles.add(role);
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, roles), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível encontrar essa role!", 404, roles), HttpStatus.NOT_FOUND);

        }
    }

    // Método adiciona uma nova role
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseModel<Roles>> post(@RequestBody Roles role) throws NotFoundExceptions{

        List<Roles> roles = new ArrayList<>();

        try {
            roleService.addRoles(role);
            roles.add(role);
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, roles), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("não foi possível adicionar essa role!", 400, roles), HttpStatus.BAD_REQUEST);

        }
    }

    // Método atualiza a role correspondendo ao id informado
    
    public ResponseEntity<ResponseModel<Roles>> update(@PathVariable Integer id, RoleDTO roleDTO) throws NotFoundExceptions{

        List<Roles> roles = new ArrayList<>();

        try {
            roles.add(roleService.update(id, roleDTO));
            return new ResponseEntity<>(new ResponseModel<>("Role atualizada com sucesso!", 200, roles), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível atualizar role!", 400, roles), HttpStatus.BAD_REQUEST);

        }

    }

    // Método deleta uma rola correspondendo ao id informado

    public ResponseEntity<ResponseModel<Roles>> delete(@PathVariable Integer id) throws NotFoundExceptions{

        List<Roles> roles = new ArrayList<>();

        Roles role = roleService.buscarPorId(id).get();

        try {
            roleService.delete(id);
            roles.add(role);
            return new ResponseEntity<>(new ResponseModel<>("Role removida com sucesso!", 200, roles), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível deletar role!", 400, roles), HttpStatus.BAD_REQUEST);
        }

    }
}
