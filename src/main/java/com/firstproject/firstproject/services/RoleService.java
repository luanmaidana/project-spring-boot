package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Roles;
import com.firstproject.firstproject.dtos.RoleDTO;
import com.firstproject.firstproject.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository repo;

    // Método retorna role por id
    public Optional<Roles> buscarPorId(Integer id) throws NotFoundExceptions{
        Optional<Roles> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    // Método busca todas as roles
    public List<Roles> buscarTodos(){

        List<Roles> obj = repo.findAll();

        return obj;

    }

    // Método deleta role
    public Roles addRoles(Roles obj){

        return repo.save(obj);

    }

    // Método atualiza role
    public Roles update(Integer id, RoleDTO roleDTO){

        Roles r = repo.getById(id);

        if(r == null){
            return null;
        }

        r.setNome(roleDTO.getNome());

        return repo.save(r);

    }

    // Método deleta role
    public Boolean delete(Integer id){

        Roles role = repo.getById(id);

        if(role == null){
            return false;
        }

        repo.delete(role);

        return true;
    }

}
