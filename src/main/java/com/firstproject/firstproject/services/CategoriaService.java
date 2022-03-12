package com.firstproject.firstproject.services;

import java.util.Optional;

import com.firstproject.firstproject.domain.Categoria;
import com.firstproject.firstproject.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private CategoriaRepository repo;

    // Método para buscar categoria por id
    public Optional<Categoria> buscar(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        
        if(obj == null) {return null;}

        return obj;
    }
}
