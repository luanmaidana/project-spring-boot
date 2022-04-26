package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Destino;
import com.firstproject.firstproject.domain.Viagem;
import com.firstproject.firstproject.dtos.ViagemDTO;
import com.firstproject.firstproject.repositories.DestinoRepository;
import com.firstproject.firstproject.repositories.ViagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class ViagemService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private ViagemRepository repo;

    @Autowired
    private DestinoRepository repoDestino;

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

    public Viagem addViagem(Viagem viagem){

        return repo.save(viagem);

    }

    public Viagem insertViagem(ViagemDTO viagemDto){

        Viagem viagem = new Viagem();

        Destino destino = repoDestino.findById(viagemDto.getDestino_id()).get();

        viagem.setPreco(viagemDto.getPreco());
        viagem.setDestino(destino);

        this.addViagem(viagem);

        return viagem;

    }

}
