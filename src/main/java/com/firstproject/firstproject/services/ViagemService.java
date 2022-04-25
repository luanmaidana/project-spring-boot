package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Viagem;
import com.firstproject.firstproject.repositories.ViagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class ViagemService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private ViagemRepository repo;

    // Método para buscar categoria por id
    public Optional<Viagem> buscarPorId(Integer id) throws NotFoundExceptions{
        Optional<Viagem> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public List<Viagem> buscarTodos(){

        List<Viagem> obj = repo.findAll();

        return obj;

    }

    public Viagem addCategoria(Viagem obj){

        obj.setId(null);

        return repo.save(obj);

    }

}
