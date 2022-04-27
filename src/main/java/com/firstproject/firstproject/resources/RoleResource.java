package com.firstproject.firstproject.resources;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Roles;
import com.firstproject.firstproject.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public ResponseEntity<List<Roles>> listarTodos(){

        return ResponseEntity.ok(roleService.buscarTodos());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Roles>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Optional<Roles> role = roleService.buscarPorId(id);

        return ResponseEntity.ok(role);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Roles> post(@RequestBody Roles role) throws NotFoundExceptions{

        try {
            return ResponseEntity.ok(roleService.addRoles(role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(role);
        }
    }

}
