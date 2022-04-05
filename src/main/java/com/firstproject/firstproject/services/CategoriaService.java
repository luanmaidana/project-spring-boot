package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Categoria;
import com.firstproject.firstproject.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class CategoriaService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private CategoriaRepository repo;

    // Método para buscar categoria por id
    public Optional<Categoria> buscar(Integer id) throws NotFoundExceptions{
        Optional<Categoria> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public List<Categoria> buscarTodos(){

        List<Categoria> obj = repo.findAll();

        return obj;

    }

    public Categoria addCategoria(Categoria obj){

        obj.setId(null);

        return repo.save(obj);

    }
}
