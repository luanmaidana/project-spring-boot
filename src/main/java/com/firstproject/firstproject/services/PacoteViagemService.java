package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.PacoteViagem;
import com.firstproject.firstproject.repositories.PacoteViagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class PacoteViagemService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private PacoteViagemRepository repo;

    // Método para buscar categoria por id
    public Optional<PacoteViagem> buscar(Integer id) throws NotFoundExceptions{
        Optional<PacoteViagem> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public List<PacoteViagem> buscarTodos(){

        List<PacoteViagem> obj = repo.findAll();

        return obj;

    }

    public PacoteViagem addPacoteViagem(PacoteViagem obj){

        obj.setId(null);

        return repo.save(obj);

    }
}
