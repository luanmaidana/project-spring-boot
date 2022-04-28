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
        Optional<Destino> destino = repo.findById(id);
        
        if(destino == null) { return null;}

        return destino;
    }

    public List<Destino> buscarTodos() throws NotFoundExceptions{

        List<Destino> destinos = repo.findAll();

        if(destinos.isEmpty()){
            throw new NotFoundExceptions(400, "Nenhum destino cadastrado!");
        }

        return destinos;

    }

    public Destino addDestino(Destino destino){

        return repo.save(destino);

    }

    public Destino update(Integer id, Destino destino){

        Destino d = repo.getById(id);

        if(d == null){

            return null;
        }

        d.setId(id);
        d.setCidade(destino.getCidade());
        d.setEstado(destino.getEstado());
        d.setPais(destino.getPais());

        return repo.save(d);

    }

    public Boolean delete(Integer id){

        Destino destino = repo.getById(id);

        if(destino == null){
            return false;
        }

        repo.delete(destino);

        return true;

    }
}
