package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Destino;
import com.firstproject.firstproject.repositories.DestinoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class DestinoService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private DestinoRepository repo;

    // Método para buscar categoria por id
    public Optional<Destino> buscarPorId(Integer id) throws NotFoundExceptions{
        Optional<Destino> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public List<Destino> buscarTodos(){

        List<Destino> obj = repo.findAll();

        return obj;

    }

    public Destino addDestino(Destino obj){

        return repo.save(obj);

    }
}
