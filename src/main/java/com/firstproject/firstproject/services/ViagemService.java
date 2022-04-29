package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Destino;
import com.firstproject.firstproject.domain.Viagem;
import com.firstproject.firstproject.dtos.ViagemDTO;
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
    private DestinoService destinoService;

    // Método para buscar viagem por id
    public Optional<Viagem> buscarPorId(Integer id) throws NotFoundExceptions{
        
        Optional<Viagem> obj;

        try {
            obj = repo.findById(id);
        } catch (Exception e) {
            obj = null;
        }
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    // Método para buscar todas as viagens cadastradas
    public List<Viagem> buscarTodos(){

        List<Viagem> obj = repo.findAll();

        if(obj.isEmpty()){
            return null;
        }

        return obj;

    }

    // Método para adicionar uma nova Viagem
    public Viagem insert(ViagemDTO viagemDto) throws NotFoundExceptions{

        Viagem viagem = new Viagem();

        Destino destino = destinoService.buscarPorId(viagemDto.getDestino_id()).get();

        viagem.setPreco(viagemDto.getPreco());
        viagem.setDestino(destino);
        viagem.setNomeHotel(viagemDto.getNomeHotel());

        return repo.save(viagem);

    }

    // Método para atualizar viagem por id

    public Viagem update(Integer id, ViagemDTO viagemDto) throws NotFoundExceptions{
        
        Viagem v = repo.findById(id).get();

        Destino destino = destinoService.buscarPorId(viagemDto.getDestino_id()).get();

        if(v == null){
            return null;
        }

       // v.setId(id);
        v.setPreco(viagemDto.getPreco());
        v.setDestino(destino);
        v.setNomeHotel(viagemDto.getNomeHotel());
        repo.save(v);

        return v;
    }

    // Método para deletar viagem pelo id

    public Boolean delete(Integer id){
        
        Viagem v = repo.getById(id);

        if(v == null){
            return false;
        }

        repo.delete(v);

        return true;
    }
}
