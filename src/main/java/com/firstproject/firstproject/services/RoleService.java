package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Roles;
import com.firstproject.firstproject.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository repo;

    public Optional<Roles> buscarPorId(Integer id) throws NotFoundExceptions{
        Optional<Roles> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public List<Roles> buscarTodos(){

        List<Roles> obj = repo.findAll();

        return obj;

    }

    public Roles addRoles(Roles obj){

        return repo.save(obj);

    }

    // public Roles updateRoles(Roles role){

    //     Optional<Roles> role = repo.findById(role.get().getId());

    //     return repo.save(role);

    // }

}
